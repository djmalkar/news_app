package com.dipesh.newsapp.news

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dipesh.kotlin.screens.main.NewsViewMvc
import com.dipesh.newsapp.datastore.entities.NewsDetailsModel
import com.dipesh.newsapp.dependencyinjection.ViewMvcFactory
import com.dipesh.newsapp.news.usecase.FetchNewsUsecase
import com.dipesh.newsapp.newsdetails.NewsDetailsActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsActivity : AppCompatActivity(), NewsViewMvc.Listener, FetchNewsUsecase.Listener {
    private lateinit var mViewMvc: NewsViewMvc

    @Inject
    lateinit var fetchNewsUsecase: FetchNewsUsecase

    @Inject
    lateinit var viewFactory: ViewMvcFactory
    private var mIsFirstTime = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewMvc = viewFactory.getMainViewMvc(null)
        mViewMvc.setTitle("News List")
        setContentView(mViewMvc.rootView)
    }

    override fun onStart() {
        super.onStart()
        mViewMvc.registerListener(this)
        fetchNewsUsecase.registerListener(this)
        if (mIsFirstTime) {
            mIsFirstTime = false
            startDataFetching()
        }
    }

    private fun startDataFetching() {
        mViewMvc.showProgress()
        fetchNewsUsecase.fetchMoviesAndNotify()
    }

    override fun onMoviesFetched(newsDetailsModel: List<NewsDetailsModel>) {
        mViewMvc.hideProgress()
        mViewMvc.populateAdapter(newsDetailsModel)
    }

    override fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }

    override fun onStop() {
        super.onStop()
        mViewMvc.unregisterListener(this)
        fetchNewsUsecase.removeListener(this)
        fetchNewsUsecase.dispose()
    }

    override fun onFetchingFailed(message: String) {
        mViewMvc.hideProgress()
        mViewMvc.displayMessage(message)
    }

    override fun onMovieClicked(newsModel: NewsDetailsModel) {
        startActivity(NewsDetailsActivity.newInstance(this, newsModel));
    }
}
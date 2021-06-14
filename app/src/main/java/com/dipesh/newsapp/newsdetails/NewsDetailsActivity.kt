package com.dipesh.newsapp.newsdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dipesh.newsapp.datastore.entities.NewsDetailsModel
import com.dipesh.newsapp.dependencyinjection.ViewMvcFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsDetailsActivity : AppCompatActivity() {

    companion object {
        private const val NEWS_MODEL = "NEWS_MODEL"
        fun newInstance(context: Context, newsDetailsModel: NewsDetailsModel): Intent {
            val intent = Intent(context, NewsDetailsActivity::class.java)
            intent.putExtra(NEWS_MODEL, newsDetailsModel)
            return intent
        }
    }

    @Inject
    lateinit var viewFactory: ViewMvcFactory
    private lateinit var mViewMvc: NewsDetailsViewMvc
    private lateinit var newsDetailsModel: NewsDetailsModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsDetailsModel = getNewsDetails()
        mViewMvc = viewFactory.getNewsDetailsViewMvc(null)
        mViewMvc.setTitle(newsDetailsModel.display_title)
        mViewMvc.setProfileImage(newsDetailsModel.multimedia.src)
        mViewMvc.setSummary(newsDetailsModel.summary_short)
        setContentView(mViewMvc.rootView)
    }

    private fun getNewsDetails(): NewsDetailsModel {
        return intent.getSerializableExtra(NEWS_MODEL) as NewsDetailsModel
    }
}
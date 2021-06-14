package com.dipesh.kotlin.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dipesh.newsapp.R
import com.dipesh.newsapp.common.toolbar.ToolbarViewMvc
import com.dipesh.newsapp.common.views.BaseObservableViewMvc
import com.dipesh.newsapp.datastore.entities.NewsDetailsModel
import com.dipesh.newsapp.dependencyinjection.ViewMvcFactory
import com.dipesh.newsapp.news.NewsRecyclerAdapter

class NewsViewMvc(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory)
    : BaseObservableViewMvc<NewsViewMvc.Listener>(), NewsRecyclerAdapter.Listener {

    interface Listener {
        fun onMovieClicked(newsModel: NewsDetailsModel)
    }

    private val mToolbarViewMvc: ToolbarViewMvc
    private val mToolbar: Toolbar
    private val mMembersList: RecyclerView
    private val mProgressBar: FrameLayout
    private val mAdapter: NewsRecyclerAdapter

    init {
        rootView = inflater.inflate(R.layout.activity_news, parent, false)
        mMembersList = findViewById(R.id.members_list)
        mProgressBar = findViewById(R.id.progress_bar)

        val layoutManager = LinearLayoutManager(context)
        mMembersList.layoutManager = layoutManager
        mAdapter = NewsRecyclerAdapter(this, viewMvcFactory)
        mMembersList.adapter = mAdapter

        mToolbar = findViewById(R.id.toolbar)
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar)
        mToolbar.addView(mToolbarViewMvc.rootView)
    }

    fun populateAdapter(news: List<NewsDetailsModel>) {
        mAdapter.bindData(news)
    }

    fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showProgress() {
        mProgressBar.visibility = View.VISIBLE
    }

    fun hideProgress() {
        mProgressBar.visibility = View.GONE
    }

    fun setTitle(title: String) {
        mToolbarViewMvc.setTitle(title)
    }

    override fun onItemClicked(newsModel: NewsDetailsModel) {
        listeners?.onMovieClicked(newsModel)
    }


}
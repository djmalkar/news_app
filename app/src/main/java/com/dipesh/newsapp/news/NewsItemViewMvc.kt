package com.dipesh.newsapp.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dipesh.newsapp.R
import com.dipesh.newsapp.common.views.BaseObservableViewMvc
import com.dipesh.newsapp.datastore.entities.NewsDetailsModel

class NewsItemViewMvc(inflater: LayoutInflater, parent: ViewGroup?)
    : BaseObservableViewMvc<NewsItemViewMvc.Listener>() {

    interface Listener {
        fun onItemClicked(newsModel: NewsDetailsModel)
    }

    private val newsImage: ImageView
    private val title: TextView
    private val summary: TextView
    private var newsModel: NewsDetailsModel? = null

    init {
        rootView = inflater.inflate(R.layout.item_member_view, parent, false)
        newsImage = findViewById(R.id.profile)
        title = findViewById(R.id.name)
        summary = findViewById(R.id.summary)
        rootView?.setOnClickListener { view: View? -> if(listeners != null) {
            newsModel?.let { listeners?.onItemClicked(it) }
        } }
    }

    fun bindData(newsModel: NewsDetailsModel) {
        this.newsModel = newsModel
        Glide.with(context).load(newsModel.multimedia.src).placeholder(R.drawable.placeholder).into(newsImage)
        title.text = newsModel.display_title
        summary.text = newsModel.headline
    }

}
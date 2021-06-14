package com.dipesh.newsapp.news

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dipesh.newsapp.datastore.entities.NewsDetailsModel
import com.dipesh.newsapp.dependencyinjection.ViewMvcFactory
import java.util.*

class NewsRecyclerAdapter(private val mListener: Listener, private val mViewMvcFactory: ViewMvcFactory)
    : RecyclerView.Adapter<NewsRecyclerAdapter.MovieViewHolder>(), NewsItemViewMvc.Listener {
    interface Listener {
        fun onItemClicked(newsModel: NewsDetailsModel)
    }

    class MovieViewHolder(val mViewMvc: NewsItemViewMvc) : RecyclerView.ViewHolder(mViewMvc.rootView!!)

    private var moviesList: List<NewsDetailsModel> = ArrayList()

    fun bindData(news: List<NewsDetailsModel>) {
        moviesList = ArrayList(news)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val viewMvc = mViewMvcFactory.getNewsItemViewMvc(parent)
        viewMvc.registerListener(this)
        return MovieViewHolder(viewMvc)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.mViewMvc.bindData(moviesList[position])
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onItemClicked(newsModel: NewsDetailsModel) {
        mListener.onItemClicked(newsModel)
    }

}
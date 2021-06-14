package com.dipesh.newsapp.dependencyinjection

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dipesh.kotlin.screens.main.NewsViewMvc
import com.dipesh.newsapp.common.toolbar.ToolbarViewMvc
import com.dipesh.newsapp.news.NewsItemViewMvc
import com.dipesh.newsapp.newsdetails.NewsDetailsViewMvc
import javax.inject.Inject

class ViewMvcFactory @Inject constructor(private val mLayoutInflater: LayoutInflater) {
    fun getMainViewMvc(parent: ViewGroup?): NewsViewMvc {
        return NewsViewMvc(mLayoutInflater, null, this)
    }

    fun getNewsDetailsViewMvc(parent: ViewGroup?): NewsDetailsViewMvc {
        return NewsDetailsViewMvc(mLayoutInflater, parent, this)
    }

    fun getNewsItemViewMvc(parent: ViewGroup?): NewsItemViewMvc {
        return NewsItemViewMvc(mLayoutInflater, parent)
    }

    fun getToolbarViewMvc(parent: ViewGroup?): ToolbarViewMvc {
        return ToolbarViewMvc(mLayoutInflater, parent)
    }
}
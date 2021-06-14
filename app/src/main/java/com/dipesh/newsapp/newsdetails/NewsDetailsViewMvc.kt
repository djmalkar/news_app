package com.dipesh.newsapp.newsdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.dipesh.kotlin.screens.common.views.BaseViewMvc
import com.dipesh.newsapp.R
import com.dipesh.newsapp.common.toolbar.ToolbarViewMvc
import com.dipesh.newsapp.dependencyinjection.ViewMvcFactory

class NewsDetailsViewMvc(inflater: LayoutInflater, parent: ViewGroup?, viewMvcFactory: ViewMvcFactory)
    : BaseViewMvc(){

    private val mToolbarViewMvc: ToolbarViewMvc
    private val mToolbar: Toolbar
    private val profileIv: ImageView
    private val titleTv: TextView
    private val summaryTv: TextView

    init {
        rootView = inflater.inflate(R.layout.activity_news_details, parent, false)
        profileIv = findViewById(R.id.profile)
        titleTv = findViewById(R.id.title)
        summaryTv = findViewById(R.id.summary)

        mToolbar = findViewById(R.id.toolbar)
        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar)
        mToolbar.addView(mToolbarViewMvc.rootView)
    }

    fun setTitle(title: String) {
        mToolbarViewMvc.setTitle(title)
        titleTv.setText(title)
    }

    fun setProfileImage(source: String) {
        Glide.with(context).load(source).placeholder(R.drawable.placeholder).into(profileIv)
    }

    fun setSummary(summary: String) {
        summaryTv.setText(summary)
    }
}
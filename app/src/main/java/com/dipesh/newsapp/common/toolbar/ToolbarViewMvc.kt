package com.dipesh.newsapp.common.toolbar

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.dipesh.kotlin.screens.common.views.BaseViewMvc
import com.dipesh.newsapp.R

class ToolbarViewMvc(inflater: LayoutInflater, parent: ViewGroup?) : BaseViewMvc() {

    private val mTxtTitle: TextView

    init {
        rootView = inflater.inflate(R.layout.layout_toolbar, parent, false)
        mTxtTitle = findViewById(R.id.txt_toolbar_title)
    }

    fun setTitle(title: String) {
        mTxtTitle.text = title
    }

}
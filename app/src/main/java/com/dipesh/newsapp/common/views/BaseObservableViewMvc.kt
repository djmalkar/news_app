package com.dipesh.newsapp.common.views

import com.dipesh.kotlin.screens.common.views.BaseViewMvc
import com.dipesh.kotlin.screens.common.views.ObservableViewMvc

abstract class BaseObservableViewMvc<ListenerType> : BaseViewMvc(),
    ObservableViewMvc<ListenerType> {
    protected var listeners: ListenerType? = null
        private set

    override fun registerListener(listener: ListenerType) {
        listeners = listener
    }

    override fun unregisterListener(listener: ListenerType) {
        listeners = null
    }

}
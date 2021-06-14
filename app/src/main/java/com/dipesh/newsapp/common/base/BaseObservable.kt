package com.dipesh.newsapp.common.base

abstract class BaseObservable<LISTENER> {
    protected var listener: LISTENER? = null
        private set

    fun registerListener(listener: LISTENER) {
        this.listener = listener
    }

    fun removeListener(listener: LISTENER) {
        this.listener = null
    }
}
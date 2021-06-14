package com.dipesh.newsapp.news

import com.dipesh.newsapp.datastore.ApiRetrofit
import com.dipesh.newsapp.datastore.networkschema.NewsResponseNetworkSchema
import javax.inject.Inject

class NewsRepository @Inject constructor (val apiRetrofit: ApiRetrofit) {

    suspend fun getMovieListing (searchText : String): NewsResponseNetworkSchema {
        return apiRetrofit.getMovieListingApi(searchText)
    }
}
package com.dipesh.newsapp.datastore

import com.dipesh.newsapp.common.Constants
import com.dipesh.newsapp.datastore.networkschema.NewsResponseNetworkSchema
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiRetrofit {

    @GET("reviews/search.json")
    suspend fun getMovieListingApi(@Query("query") queryText: String,
                                   @Query("api-key") apiKey: String = Constants.apiKey): NewsResponseNetworkSchema

}
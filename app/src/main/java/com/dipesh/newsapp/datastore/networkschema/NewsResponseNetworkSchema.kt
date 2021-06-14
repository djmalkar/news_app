package com.dipesh.newsapp.datastore.networkschema

data class NewsResponseNetworkSchema(
    val status: String,
    val copyright: String?,
    val has_more: String?,
    val num_results: String?,
    val results: List<NewsDetailsNetworkSchema>?
)

data class NewsDetailsNetworkSchema (
    val display_title: String,
    val mpaa_rating: String,
    val critics_pick: Int,
    val byline: String,
    val headline: String,
    val summary_short: String,
    val multimedia: MultimediaNetworkSchema
)

data class MultimediaNetworkSchema (
    val type: String,
    val src: String,
    val height: Int,
    val width: Int,
)

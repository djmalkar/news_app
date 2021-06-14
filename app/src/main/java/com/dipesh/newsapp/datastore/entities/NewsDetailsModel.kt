package com.dipesh.newsapp.datastore.entities

import java.io.Serializable

data class NewsDetailsModel(
    val display_title: String,
    val mpaa_rating: String,
    val critics_pick: Int,
    val byline: String,
    val headline: String,
    val summary_short: String,
    val multimedia: MultimediaModel
) : Serializable

data class MultimediaModel(
    val type: String,
    val src: String,
    val height: Int,
    val width: Int,
) : Serializable

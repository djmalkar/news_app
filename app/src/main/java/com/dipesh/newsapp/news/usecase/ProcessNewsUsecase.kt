package com.dipesh.newsapp.news.usecase

import com.dipesh.newsapp.datastore.entities.MultimediaModel
import com.dipesh.newsapp.datastore.entities.NewsDetailsModel
import com.dipesh.newsapp.datastore.networkschema.MultimediaNetworkSchema
import com.dipesh.newsapp.datastore.networkschema.NewsDetailsNetworkSchema
import java.util.ArrayList
import javax.inject.Inject

class ProcessNewsUsecase @Inject constructor() {

    fun getMovieList(newsListNetworkSchema: List<NewsDetailsNetworkSchema>): List<NewsDetailsModel> {
        val newsDetailsList: MutableList<NewsDetailsModel> = ArrayList()
        for (newsNetworkSchema in newsListNetworkSchema) {
            val memberTable = NewsDetailsModel(
                newsNetworkSchema.display_title,
                newsNetworkSchema.mpaa_rating,
                newsNetworkSchema.critics_pick,
                newsNetworkSchema.byline,
                newsNetworkSchema.headline,
                newsNetworkSchema.summary_short,
                getMultiMediaModel(newsNetworkSchema.multimedia),
            )
            newsDetailsList.add(memberTable)
        }
        return newsDetailsList
    }

    private fun getMultiMediaModel(multimedia: MultimediaNetworkSchema): MultimediaModel {
        return MultimediaModel(multimedia.type, multimedia.src, multimedia.height, multimedia.width)
    }
}
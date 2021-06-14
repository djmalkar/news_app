package com.dipesh.newsapp.news.usecase

import com.dipesh.newsapp.common.base.BaseObservable
import com.dipesh.newsapp.datastore.entities.NewsDetailsModel
import com.dipesh.newsapp.datastore.entities.MultimediaModel
import com.dipesh.newsapp.datastore.networkschema.NewsDetailsNetworkSchema
import com.dipesh.newsapp.datastore.networkschema.NewsResponseNetworkSchema
import com.dipesh.newsapp.datastore.networkschema.MultimediaNetworkSchema
import com.dipesh.newsapp.news.NewsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

class FetchNewsUsecase
@Inject constructor(private val newsRepository: NewsRepository, private val processNewsUsecase: ProcessNewsUsecase) :
    BaseObservable<FetchNewsUsecase.Listener>() {

    interface Listener {
        fun onMoviesFetched(newsDetailsModel: List<NewsDetailsModel>)
        fun isNetworkConnected(): Boolean
        fun onFetchingFailed(errorMessage: String)
    }

    companion object {
        private const val TAG = "FetchMovieAndProcessUsecase"
    }

    private var job: Job? = null

    fun fetchMoviesAndNotify(text: String = "") {
        if (listener?.isNetworkConnected() == false) {
            listener?.onFetchingFailed("Please connect to valid internet connection")
        } else {
            job = CoroutineScope(Dispatchers.Main).launch {
                val movieListNetworkSchema = newsRepository.getMovieListing(text)
                processMovieSchema(movieListNetworkSchema);
            }
        }
    }

    private fun processMovieSchema(movieListNetworkSchema: NewsResponseNetworkSchema) {
        if (movieListNetworkSchema.results.isNullOrEmpty()) {
            listener?.onFetchingFailed("No Result found")
        } else {
            val movieDetailList = processNewsUsecase.getMovieList(movieListNetworkSchema.results)
            val sortedMovieList = movieDetailList.sortedBy { movieDetail -> movieDetail.display_title }
            listener?.onMoviesFetched(sortedMovieList)
        }
    }

    fun dispose() {
        job?.apply { cancel() }
    }

}
package com.fawry.movies.ui.activity

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fawry.movies.R
import com.fawry.movies.data.datastore.DataStoreManager
import com.fawry.movies.model.database.MovieDatabaseModel
import com.fawry.movies.model.domain.MovieDomainModel
import com.fawry.movies.repository.MovieRepository
import com.fawry.movies.util.ext.convert.asDomainModel
import com.infinity.movieapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    val dataStoreManager: DataStoreManager,
    val movieRepository: MovieRepository,
    val resource: Resources
) : ViewModel() {


    private val moviesMutable: MutableLiveData<Resource<List<MovieDomainModel>>> =
        MutableLiveData()
    val popularMovies: LiveData<Resource<List<MovieDomainModel>>>
        get() = moviesMutable


    init {
        viewModelScope.launch {
            dataStoreManager.lastUpdateFlow.collect {
                if (System.currentTimeMillis() - it > (4 * 60 * 60 * 1000))
                    movieRepository.refreshPopularMovieList()
            }
        }
        getMovies()
    }

    private fun getMovies() = viewModelScope.launch(Dispatchers.IO) {
        movieRepository.getLatestMovies().collect {
            handleMoviesResponse(it, moviesMutable)
        }
    }


    private fun handleMoviesResponse(
        movies: List<MovieDatabaseModel>,
        list: MutableLiveData<Resource<List<MovieDomainModel>>>,
    ) {
        list.postValue(Resource.Loading())
        if (movies.asDomainModel().isNotEmpty()) {

            list.postValue(
                Resource.Success(
                    movies.asDomainModel(),
                    responseCode = 0
                )
            )
        } else {
            list.postValue(
                Resource.Error(
                    resource.getString(R.string.no_movies),
                    responseCode = 0, data = emptyList()
                )
            )
        }
    }

}
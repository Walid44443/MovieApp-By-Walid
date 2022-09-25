package com.fawry.movies.repository

import android.util.Log
import com.fawry.movies.data.database.MovieDatabase
import com.fawry.movies.data.datastore.DataStoreManager
import com.fawry.movies.data.network.RetrofitInstance
import com.fawry.movies.model.database.MovieDatabaseModel
import com.fawry.movies.util.ResponseHandler
import com.fawry.movies.util.enums.Status.*
import com.fawry.movies.util.ext.convert.asDataBaseModel
import com.infinity.movieapp.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepository @Inject constructor(
    val dataStoreManager: DataStoreManager,
    val db: MovieDatabase,
    val responseHandler: ResponseHandler
) {
    suspend fun refreshPopularMovieList(): Resource<List<MovieDatabaseModel>> {
        return try {
            val response = RetrofitInstance.api.getMovies()
            if (response.isSuccessful) {
                val result = responseHandler.handleSuccess(
                    response.body()!!.results.asDataBaseModel(popularMovies = true),
                    response.code()
                )
                handleResult(result)
                return result
            } else {
                responseHandler.handleException(response.errorBody()!!.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
            responseHandler.handleException(e)
        }
    }

    private fun handleResult(result: Resource<List<MovieDatabaseModel>>) {
        CoroutineScope(Dispatchers.Default).launch {
            when (result.status) {
                ERROR -> {
                    withContext(Dispatchers.IO) {
                        Log.e("NETWORK ERROR", result.message.toString())
                    }

                }
                SUCCESS -> {
                    addMoviesToDb(result.data!!)
                    dataStoreManager.setLastUpdate(System.currentTimeMillis())
                }
                LOADING -> {
                    Log.e("NETWORK LOADING", result.message.toString())
                }
                else -> {
                    Log.e("NETWORK ERROR", result.message.toString())
                }
            }
        }
    }

    private suspend fun addMoviesToDb(movie: List<MovieDatabaseModel>) =
        db.getMovieDAO().upsert(movie)


    fun getLatestMovies() = db.getMovieDAO().getAllMovies()
}


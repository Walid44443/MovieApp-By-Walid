package com.fawry.movies.data.network.services


import com.fawry.movies.model.api.Response.MoviesResponse

import com.infinity.movieapp.util.Constants.Companion.API_KEY
import retrofit2.Response

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

   @GET("/3/movie/popular")
   suspend fun getMovies(
       @Query("api_key")
       api_key: String = API_KEY
   ) : Response<MoviesResponse>
}
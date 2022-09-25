package com.fawry.movies.model.api.Response

import com.fawry.movies.model.api.Movie
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MoviesResponse(
    @field:Json(name ="page" )
    var page: Int,
    @field:Json(name = "results")
    val results: List<Movie>,
    @field:Json(name ="total_pages" )
    val total_pages: Int,
    @field:Json(name ="total_results" )
    val total_results: Int
)
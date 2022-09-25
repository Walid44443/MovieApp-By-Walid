package com.fawry.movies.model.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    @field:Json(name = "id")
    val id: String?,
    @field:Json(name = "backdrop_path")
    val backdrop_path: String?,
    @field:Json(name = "original_title")
    val original_title: String?,
    @field:Json(name = "overview")
    val overview: String?,
    @field:Json(name = "popularity")
    val popularity: String?,
    @field:Json(name = "poster_path")
    val poster_path: String?,
    @field:Json(name = "release_date")
    val release_date: String?,
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "vote_average")
    val vote_average: String?
)
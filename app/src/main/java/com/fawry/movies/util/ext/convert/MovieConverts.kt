package com.fawry.movies.util.ext.convert

import com.fawry.movies.model.api.Movie
import com.fawry.movies.model.database.MovieDatabaseModel
import com.fawry.movies.model.domain.MovieDomainModel


fun List<MovieDatabaseModel>.asDomainModel(): List<MovieDomainModel> {
    return map {
        MovieDomainModel(
            id = it.id,
            backdrop_path = it.backdrop_path,
            original_title = it.original_title,
            overview = it.overview,
            popularity = it.popularity,
            poster_path = it.poster_path,
            release_date = it.release_date,
            title = it.title,
            vote_average = it.vote_average,
            popular = it.popular,
            latest = it.latest

        )
    }
}


fun com.fawry.movies.model.domain.MovieDomainModel.asDataBaseModel() =
    MovieDatabaseModel(
        id = this.id,
        backdrop_path = this.backdrop_path,
        original_title = this.original_title,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        title = this.title,
        vote_average = this.vote_average,
        popular = this.popular,
        latest = this.latest,
        isSaved = true
    )


fun List<Movie>.asDataBaseModel(
    popularMovies: Boolean = false,
    latestMovies: Boolean = false
): List<MovieDatabaseModel> {
    return map {
        MovieDatabaseModel(
            id = it.id!!,
            backdrop_path = it.backdrop_path!!,
            original_title = it.original_title!!,
            overview = it.overview!!,
            popularity = it.popularity!!,
            poster_path = it.poster_path!!,
            release_date = it.release_date!!,
            title = it.title!!,
            vote_average = it.vote_average!!,
            popular = popularMovies,
            latest = latestMovies
        )
    }
}

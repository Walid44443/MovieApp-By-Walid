package com.fawry.movies.model.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "movies")
@Parcelize
data class MovieDatabaseModel(
    @PrimaryKey
    val id: String,
    val backdrop_path: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val vote_average: String,
    val popular: Boolean,
    val latest: Boolean,
    var isSaved: Boolean = false
) : Parcelable

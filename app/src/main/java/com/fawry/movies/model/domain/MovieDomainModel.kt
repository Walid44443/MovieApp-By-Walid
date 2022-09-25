package com.fawry.movies.model.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Entity(tableName ="movies")
@Parcelize
@JsonClass(generateAdapter = true)
data class MovieDomainModel(
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
    val popular : Boolean,
    val latest : Boolean
) : Parcelable
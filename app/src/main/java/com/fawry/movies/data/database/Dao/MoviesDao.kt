package com.fawry.movies.data.database.Dao

import androidx.room.*
import com.fawry.movies.model.database.MovieDatabaseModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(movie: List<MovieDatabaseModel>): List<Long>

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieDatabaseModel>>

    @Delete
    suspend fun deleteMovie(movie: MovieDatabaseModel)
}
package com.fawry.movies.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fawry.movies.data.database.Dao.MoviesDao
import com.fawry.movies.model.database.MovieDatabaseModel

@Database(entities = [MovieDatabaseModel::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun getMovieDAO(): MoviesDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(Lock) {
            instance ?: createDatabase(context).also { instance = it }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(

                context.applicationContext,
                MovieDatabase::class.java,
                "movieDatabase.db"
            ).build()
    }
}
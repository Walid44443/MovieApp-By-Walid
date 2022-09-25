package com.fawry.movies.module

import android.content.Context
import com.fawry.movies.data.database.MovieDatabase
import com.fawry.movies.data.datastore.DataStoreManager
import com.fawry.movies.repository.MovieRepository
import com.fawry.movies.util.ResponseHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ViewModelModule {

    @Provides
    fun provideMovieAppDatabase(@ApplicationContext context: Context) = MovieDatabase(context)

    @Provides
    fun provideResponseHandler(@ApplicationContext context: Context) =
        ResponseHandler(context.resources)


    @Provides
    fun provideMovieRepo(
        dataStoreManager: DataStoreManager,
        movieDatabase: MovieDatabase,
        responseHandler: ResponseHandler
    ) =
        MovieRepository(dataStoreManager, movieDatabase, responseHandler)

    @Provides
    fun provideResources(@ApplicationContext context: Context) = context.resources


    @Provides
    fun provideDataStoreManger(@ApplicationContext context: Context) = DataStoreManager(context)


}
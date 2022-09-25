package com.fawry.movies.util

import android.content.res.Resources
import com.fawry.movies.R
import com.fawry.movies.util.enums.ErrorCodes
import com.infinity.movieapp.util.ConnectivityInterceptor
import com.infinity.movieapp.util.Resource
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

open class ResponseHandler
@Inject constructor(val resources: Resources) {
    fun <T : Any> handleSuccess(data: T?, responseCode: Int): Resource<T> {
        return Resource.Success(data, responseCode)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> Resource.Error(getErrorMessage(e.code(), resources), null, -5)
            is ConnectivityInterceptor.NoNetworkException -> Resource.NoInternetConnection(
                resources.getString(R.string.no_internet),
                null,
                -1
            )
            is UnknownHostException -> Resource.NoInternetConnection(
                resources.getString(R.string.no_internet),
                null,
                -2
            )
            is ConnectException -> Resource.NoInternetConnection(
                resources.getString(R.string.no_internet),
                null,
                -3
            )
            is SocketTimeoutException -> Resource.Error(
                getErrorMessage(ErrorCodes.SocketTimeOut.code, resources),
                null,
                -4
            )
            else -> Resource.Error(getErrorMessage(Int.MAX_VALUE, resources), null, -1)
        }
    }

    fun <T : Any> handleException(message: String): Resource<T> {
        return Resource.Error(getErrorMessage(message.toInt(), resources), null, -1)
    }

    fun <T : Any> handleException(statusCode: Int): Resource<T> {
        return Resource.Error(getErrorMessage(statusCode, resources), null, -1)
    }
}

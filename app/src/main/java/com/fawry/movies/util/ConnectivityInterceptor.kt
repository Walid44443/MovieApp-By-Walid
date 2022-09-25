package com.infinity.movieapp.util

import android.content.Context
import androidx.lifecycle.asLiveData
import com.fawry.movies.util.NetworkStatusTracker
import com.fawry.movies.util.enums.MyState
import com.fawry.movies.util.enums.Status
import com.fawry.movies.util.ext.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import okhttp3.Interceptor
import okhttp3.Response
import okio.IOException


open class ConnectivityInterceptor(context : Context) : Interceptor {

    @OptIn(FlowPreview::class)
    val state =
        NetworkStatusTracker(context).networkStatus
            .map(
                onAvailable = { MyState.Fetched },
                onUnavailable = { MyState.Error }
            )
            .asLiveData(Dispatchers.IO)

    private val isConnected: Boolean
        get() {
            return when(state.value){
                is MyState.Fetched ->{
                    return true
                }
                is MyState.Error->{
                    return false
                }
                else -> false
            }
        }



    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        if (!isConnected) {
            throw NoNetworkException()
        }
        return chain.proceed(originalRequest)
}

    class NoNetworkException internal constructor() :
        IOException(Status.NO_INTERNET_CONNECTION.toString())
}
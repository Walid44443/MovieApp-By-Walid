package com.fawry.movies.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.fawry.movies.util.enums.NetworkStatus
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

class NetworkStatusTracker(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkStatus = callbackFlow<NetworkStatus> {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onUnavailable() {
                println("onUnavailable")
                this@callbackFlow.trySend(NetworkStatus.Unavailable).isSuccess
            }

            override fun onAvailable(network: Network) {
                println("onAvailable")
                trySend(NetworkStatus.Available).isSuccess
            }

            override fun onLost(network: Network) {
                println("onLost")
                trySend(NetworkStatus.Unavailable).isSuccess
            }
        }

        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        connectivityManager.registerNetworkCallback(request, networkStatusCallback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(networkStatusCallback)
        }
    }
}

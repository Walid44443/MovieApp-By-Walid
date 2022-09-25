package com.fawry.movies.util.ext

import com.fawry.movies.util.enums.NetworkStatus
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@FlowPreview
inline fun <Result> Flow<NetworkStatus>.map(
    crossinline onUnavailable: suspend () -> Result,
    crossinline onAvailable: suspend () -> Result
): Flow<Result> = map { status ->
    when (status) {
        NetworkStatus.Unavailable -> onUnavailable()
        NetworkStatus.Available -> onAvailable()
    }
}
package com.fawry.movies.util.enums

sealed class MyState {
    object Fetched : MyState()
    object Error : MyState()
}
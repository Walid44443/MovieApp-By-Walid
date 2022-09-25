package com.fawry.movies.di

import com.fawry.movies.ui.activity.MainActivity
import dagger.Component

@Component
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}
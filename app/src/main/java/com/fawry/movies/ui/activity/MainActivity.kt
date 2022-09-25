package com.fawry.movies.ui.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.fawry.movies.R
import com.fawry.movies.data.database.MovieDatabase
import com.fawry.movies.data.datastore.DataStoreManager
import com.fawry.movies.databinding.ActivityMainBinding
import com.fawry.movies.repository.MovieRepository
import com.fawry.movies.util.ResponseHandler
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

    }
}
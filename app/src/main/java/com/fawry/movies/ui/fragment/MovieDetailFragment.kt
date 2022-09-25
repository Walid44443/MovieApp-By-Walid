package com.fawry.movies.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.fawry.movies.R
import com.fawry.movies.databinding.FragmentMovieDetailBinding
import com.fawry.movies.ui.activity.MainActivityViewModel
import com.fawry.movies.util.ext.convert.asDataBaseModel
import com.google.android.material.snackbar.Snackbar

class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {


    private val args: MovieDetailFragmentArgs by navArgs()
    lateinit var binding: FragmentMovieDetailBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMovieDetailBinding.bind(view)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.movie = args.movie
    }


}
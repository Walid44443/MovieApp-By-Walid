package com.fawry.movies.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.fawry.movies.R
import com.fawry.movies.databinding.FragmentMovieBinding
import com.fawry.movies.ui.activity.MainActivityViewModel
import com.fawry.movies.util.binding.hide
import com.fawry.movies.util.binding.show
import com.fawry.movies.util.binding.toast
import com.infinity.movieapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularMoviesFragment : Fragment(R.layout.fragment_movie) {

    private val viewModel : MainActivityViewModel by activityViewModels()
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var binding: FragmentMovieBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieBinding.bind(view)

        setUpRecyclerView()


        moviesAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("movie",it)
            }
            findNavController().navigate(R.id.action_movieFragment_to_movieDetailFragment, bundle)
        }

        viewModel.popularMovies.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.hide()
                    //database to domain model
                    moviesAdapter.differ.submitList(response.data!!)
                }
                is Resource.Loading -> {
                    binding.progressBar.show()
                }
                is Resource.Error -> {
                    response.message.let { message ->
                        binding.progressBar.hide()
                        context?.toast(message.toString())
                    }
                }
                else -> {
                    context?.toast(response.message.toString())
                }
            }


        }
    }

    private fun setUpRecyclerView() {
        moviesAdapter = MoviesAdapter()
        binding.recyclerView.adapter = moviesAdapter
    }



}

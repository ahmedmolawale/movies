package com.ahmedmolawale.movies.ui.movies.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmedmolawale.movies.databinding.DiscoverMoviesFragmentBinding
import com.ahmedmolawale.movies.model.MoviePresentation
import com.ahmedmolawale.movies.ui.movies.MoviesFragmentDirections
import com.ahmedmolawale.movies.ui.movies.adapter.MoviesAdapter
import com.ahmedmolawale.movies.ui.utils.initRecyclerViewUsingGridLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DiscoverMovieFragment : Fragment() {

    private var _binding: DiscoverMoviesFragmentBinding? = null
    private val binding get() = _binding!!
    private val movieDiscoverViewModel: MovieDiscoverViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DiscoverMoviesFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = movieDiscoverViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // movieDiscoverViewModel.discoverMovies()
        setupListAdapter()
    }

    private fun setupListAdapter() {
        moviesAdapter =
            MoviesAdapter { movie -> openMovieDetails(movie) }
        context?.let {
            binding.discoverMovieList.initRecyclerViewUsingGridLayout(it, 2)
        }
        binding.discoverMovieList.adapter = moviesAdapter
    }

    private fun openMovieDetails(movie: MoviePresentation) {
        val action = MoviesFragmentDirections
            .actionMoviesFragmentToMovieDetailsFragment(movie)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

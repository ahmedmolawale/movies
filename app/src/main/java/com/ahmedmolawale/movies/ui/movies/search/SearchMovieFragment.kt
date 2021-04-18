package com.ahmedmolawale.movies.ui.movies.search

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahmedmolawale.movies.databinding.SearchMoviesFragmentBinding
import com.ahmedmolawale.movies.model.MoviePresentation
import com.ahmedmolawale.movies.ui.movies.MoviesFragmentDirections
import com.ahmedmolawale.movies.ui.movies.adapter.MoviesAdapter
import com.ahmedmolawale.movies.ui.utils.initRecyclerViewUsingGridLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class SearchMovieFragment : Fragment() {

    private var _binding: SearchMoviesFragmentBinding? = null
    private val binding get() = _binding!!
    private var SPAN_COUNT = 2
    private val movieSearchViewModel: MovieSearchViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentOrientation = resources.configuration.orientation
        SPAN_COUNT = if (currentOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            4
        } else {
            2
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SearchMoviesFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = movieSearchViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.search.addTextChangedListener { a ->
            a?.let { movieSearchViewModel.searchMovies(it.toString()) }
        }
        setupListAdapter()
    }

    private fun setupListAdapter() {
        moviesAdapter =
            MoviesAdapter { movie -> openMovieDetails(movie) }
        context?.let {
            binding.searchMovieList.initRecyclerViewUsingGridLayout(it, SPAN_COUNT)
        }
        binding.searchMovieList.adapter = moviesAdapter
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

package com.ahmedmolawale.movies.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.ahmedmolawale.movies.databinding.MovieDetailsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: MovieDetailsFragmentBinding? = null
    private val binding get() = _binding!!
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        binding.viewModel = movieDetailsViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsViewModel.setMovieDetail(args.movie)
        binding.header.headerTitle.text = args.movie.title
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

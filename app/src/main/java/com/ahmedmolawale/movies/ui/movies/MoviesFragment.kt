package com.ahmedmolawale.movies.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ahmedmolawale.movies.R
import com.ahmedmolawale.movies.databinding.MoviesFragmentBinding
import com.ahmedmolawale.movies.ui.movies.discover.DiscoverMovieFragment
import com.ahmedmolawale.movies.ui.movies.search.SearchMovieFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.header.headerTitle.text = context?.getString(R.string.movies)
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.pager.adapter =
            PageAdapter(
                childFragmentManager
            )
        binding.pager.offscreenPageLimit = 2
        binding.tabLayout.setupWithViewPager(binding.pager)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    @ExperimentalCoroutinesApi
    inner class PageAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> {
                    SearchMovieFragment()
                }
                else -> {
                    DiscoverMovieFragment()
                }
            }
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Search"
                else -> "Discover"
            }
        }
    }
}

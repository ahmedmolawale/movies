package com.ahmedmolawale.movies.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ahmedmolawale.movies.R
import com.ahmedmolawale.movies.databinding.MovieItemBinding
import com.ahmedmolawale.movies.model.MoviePresentation

/**
 * Adapter for the [MoviePresentation]'s list.
 */

typealias MovieClickListener = (MoviePresentation) -> Unit

class MoviesAdapter(private val onClick: MovieClickListener) :
    ListAdapter<MoviePresentation, MoviesAdapter.ViewHolder>(
        CharacterDiffCallback()
    ) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(onClick, item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    class ViewHolder private constructor(private val binding: MovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(onClick: MovieClickListener, item: MoviePresentation) {
            binding.root.setOnClickListener {
                onClick(item)
            }
            binding.movie = item
            binding.movieImage.load(item.posterPath) {
                crossfade(true)
                placeholder(R.drawable.movie_image_default_bg)
                error(R.drawable.movie_image_default_bg)
            }
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }
}

/**
 * Callback for calculating the diff between two non-null items in a list.
 *
 * Used by ListAdapter to calculate the minimum number of changes between and old list and a new
 * list that's been passed to `submitList`.
 */
class CharacterDiffCallback : DiffUtil.ItemCallback<MoviePresentation>() {
    override fun areItemsTheSame(
        oldItem: MoviePresentation,
        newItem: MoviePresentation
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MoviePresentation,
        newItem: MoviePresentation
    ): Boolean {
        return oldItem == newItem
    }
}

/**
 * [BindingAdapter]s for the [MoviePresentation]s list.
 */
@BindingAdapter("search_movies")
fun setSearchMovieItems(listView: RecyclerView, movies: List<MoviePresentation>?) {
    movies?.let {
        (listView.adapter as MoviesAdapter).submitList(movies)
    }
}

@BindingAdapter("discover_movies")
fun setDiscoverMovieItems(listView: RecyclerView, movies: List<MoviePresentation>?) {
    movies?.let {
        (listView.adapter as MoviesAdapter).submitList(movies)
    }
}

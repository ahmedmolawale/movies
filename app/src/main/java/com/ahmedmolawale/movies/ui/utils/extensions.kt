package com.ahmedmolawale.movies.ui.utils

import android.content.Context
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.ahmedmolawale.movies.R
import com.google.android.material.imageview.ShapeableImageView

fun RecyclerView.initRecyclerViewUsingGridLayout(context: Context, span: Int) {
    val gridLayoutManager = GridLayoutManager(context, span)
    layoutManager = gridLayoutManager
}

/**
 * [BindingAdapter]s for the [ImageView].
 */
@BindingAdapter("backdropUrl")
fun setImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        imageView.load(imageUrl) {
            placeholder(R.drawable.movie_backdrop_default_bg)
            error(R.drawable.movie_backdrop_default_bg)
        }
    }
}

/**
 * [BindingAdapter]s for the [ShapeableImageView].
 */
@BindingAdapter("posterUrl")
fun setImage(imageView: ShapeableImageView, imageUrl: String?) {
    imageUrl?.let {
        imageView.load(imageUrl) {
            placeholder(R.drawable.movie_image_default_bg)
            error(R.drawable.movie_backdrop_default_bg)
        }
    }
}

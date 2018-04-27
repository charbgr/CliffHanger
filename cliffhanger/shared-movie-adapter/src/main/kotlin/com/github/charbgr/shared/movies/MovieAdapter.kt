package com.github.charbgr.shared.movies

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import charbgr.github.com.shared_movie_adapter.R
import com.github.charbgr.baseadapter.BaseRvAdapter
import com.github.charbgr.cliffhanger.api_tmdb.TmdbHelper
import com.github.charbgr.cliffhanger.shared.extensions.render
import com.github.charbgr.cliffhanger.shared.views.imageview.MovieImageView
import com.github.charbgr.shared.movies.MovieAdapterItem.ViewTypes

class MovieAdapter : BaseRvAdapter<MovieAdapterItem>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    return when (viewType) {
      ViewTypes.MOVIE -> {
        val itemView = parent.render(R.layout.item_home_movie)
        MovieViewHolder(itemView)
      }
      else -> {
        throwUnsupportedViewType(viewType)
      }
    }
  }

  inner class MovieViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private var layout: ConstraintLayout = itemView.findViewById(R.id.item_movie_layout)
    private var movieNameTv: TextView = itemView.findViewById(R.id.item_movie_name)
    private var moviePosterIv: MovieImageView = itemView.findViewById(R.id.item_movie_poster)


    override fun bind(item: MovieAdapterItem, position: Int) {
      item as MovieListViewModel
      applyConstraints(item, position)
      movieNameTv.text = item.movie.title
      moviePosterIv.bindBackdrop(TmdbHelper.bestBackdrop(item.movie.backdropPath))
    }

    override fun clear() {
      moviePosterIv.clear()
    }

    private fun applyConstraints(movie: MovieListViewModel, position: Int) {
      ConstraintSet().apply {
        clone(layout)
        setDimensionRatio(moviePosterIv.id, movie.getAspectRatio(position))
        applyTo(layout)
      }
    }
  }
}

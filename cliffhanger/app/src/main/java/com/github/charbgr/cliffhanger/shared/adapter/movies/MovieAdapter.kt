package com.github.charbgr.cliffhanger.shared.adapter.movies

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.detail.NavigateToMovieDetail
import com.github.charbgr.cliffhanger.shared.adapter.BaseRvAdapter
import com.github.charbgr.cliffhanger.shared.adapter.movies.MovieAdapterItem.ViewTypes
import com.github.charbgr.cliffhanger.shared.extensions.render
import com.github.charbgr.cliffhanger.shared.views.imageview.MovieImageView

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
      moviePosterIv.bindBackdrop(item.movie)

      layout.setOnClickListener {
        NavigateToMovieDetail(it.context, item.movie).execute()
      }
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

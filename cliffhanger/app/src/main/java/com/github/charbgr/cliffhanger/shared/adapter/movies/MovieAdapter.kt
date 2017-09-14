package com.github.charbgr.cliffhanger.features.home.movies

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import android.view.ViewGroup
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.movies.MovieAdapterItem.ViewTypes
import com.github.charbgr.cliffhanger.shared.adapter.BaseRvAdapter
import com.github.charbgr.cliffhanger.shared.extensions.render
import kotlinx.android.synthetic.main.item_movie.view.item_movie_layout
import kotlinx.android.synthetic.main.item_movie.view.item_movie_name
import kotlinx.android.synthetic.main.item_movie.view.item_movie_poster

class MovieAdapter : BaseRvAdapter<MovieAdapterItem>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    return when (viewType) {
      ViewTypes.MOVIE -> {
        val itemView = parent.render(R.layout.item_movie)
        MovieViewHolder(itemView)
      }
      else -> {
        throwUnsupportedViewType(viewType)
      }
    }
  }

  inner class MovieViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun bind(item: MovieAdapterItem, position: Int) {
      item as MovieListViewModel
      applyConstraints(item, position)
      itemView.item_movie_name.text = item.movie.title
      itemView.item_movie_poster.bindImage(item.movie)
    }

    override fun clear() {
      itemView.item_movie_poster.clear()
    }

    private fun applyConstraints(movie: MovieListViewModel, position: Int) {
      val constraintLayout: ConstraintLayout = itemView.item_movie_layout
      ConstraintSet().apply {
        clone(constraintLayout)
        setDimensionRatio(itemView.item_movie_poster.id, movie.getAspectRatio(position))
        applyTo(constraintLayout)
      }
    }
  }
}

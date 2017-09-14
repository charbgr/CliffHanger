package com.github.charbgr.cliffhanger.features.home.movies

import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.features.home.movies.MovieAdapterItem.ViewTypes

class MovieItem(val movie: Movie) : MovieAdapterItem {
  override fun getItemViewType(): Int = ViewTypes.MOVIE
  override fun getSpanSize(position: Int): Int = if (position % 5 == 0) 2 else 1

  fun getAspectRatio(position: Int): String {
    if (getSpanSize(position) == 2) {
      return "16:10"
    } else {
      return "9:14"
    }
  }
}

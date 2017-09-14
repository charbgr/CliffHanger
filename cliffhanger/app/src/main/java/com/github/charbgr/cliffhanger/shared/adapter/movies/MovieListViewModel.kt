package com.github.charbgr.cliffhanger.features.home.movies

import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.features.home.movies.MovieAdapterItem.ViewTypes

class MovieListViewModel(val movie: Movie) : MovieAdapterItem {
  override fun getItemViewType(): Int = ViewTypes.MOVIE
  override fun getSpanSize(position: Int): Int = if (position % 5 == 0) 4 else 2

  fun getAspectRatio(position: Int): String {
    if (getSpanSize(position) == 4) {
      return "10:10"
    } else {
      return "11:16"
    }
  }
}

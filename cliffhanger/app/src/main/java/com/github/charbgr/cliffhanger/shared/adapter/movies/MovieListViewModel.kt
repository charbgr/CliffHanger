package com.github.charbgr.cliffhanger.shared.adapter.movies

import com.github.charbgr.cliffhanger.domain.MiniMovie
import com.github.charbgr.cliffhanger.shared.adapter.movies.MovieAdapterItem.ViewTypes

class MovieListViewModel(val movie: MiniMovie) : MovieAdapterItem {
  override fun getItemViewType(): Int = ViewTypes.MOVIE
  override fun getSpanSize(position: Int): Int = if (position % 5 == 0) 4 else 2

  fun getAspectRatio(position: Int): String {
    if (getSpanSize(position) == 4) {
      return "6:10"
    } else {
      return "11:16"
    }
  }
}

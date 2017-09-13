package com.github.charbgr.cliffhanger.features.home.movies

import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.domain.Movie

class MovieItem(val movie: Movie) : MovieAdapterItem {
  override fun getItemViewType(): Int = R.layout.item_movie
  override fun getSpanSize(position: Int): Int = if (position % 5 == 0) 2 else 1
}

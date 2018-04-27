package com.github.charbgr.feature.home.adapter

import com.github.charbgr.feature.home.adapter.MovieGroupItem.ViewTypes
import com.github.charbgr.shared.movies.MovieAdapterItem

class MovieCarouselItem(val movieAdapterItems: List<MovieAdapterItem>) : MovieGroupItem {
  override fun getItemViewType(): Int = ViewTypes.MOVIES_CAROUSEL
}

package com.github.charbgr.cliffhanger.feature.home.adapter

import com.github.charbgr.cliffhanger.feature.home.adapter.MovieGroupItem.ViewTypes
import com.github.charbgr.cliffhanger.shared.movies.MovieAdapterItem

class MovieCarouselItem(val movieAdapterItems: List<MovieAdapterItem>) : MovieGroupItem {
  override fun getItemViewType(): Int = ViewTypes.MOVIES_CAROUSEL
}

package com.github.charbgr.cliffhanger.features.home.group

import com.github.charbgr.cliffhanger.features.home.group.MovieGroupItem.ViewTypes
import com.github.charbgr.cliffhanger.shared.adapter.movies.MovieAdapterItem

class MovieCarouselItem(val movieAdapterItems: List<MovieAdapterItem>) : MovieGroupItem {
  override fun getItemViewType(): Int = ViewTypes.MOVIES_CAROUSEL
}

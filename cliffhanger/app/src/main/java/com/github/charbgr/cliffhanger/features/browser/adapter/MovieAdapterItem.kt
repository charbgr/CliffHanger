package com.github.charbgr.cliffhanger.features.browser.adapter

import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.features.browser.adapter.BrowserAdapterItem.ViewTypes

class MovieAdapterItem(val movie: Movie) : BrowserAdapterItem {
  override fun getItemViewType(): Int = ViewTypes.MOVIES
}

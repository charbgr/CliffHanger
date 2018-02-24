package com.github.charbgr.cliffhanger.features.browser.adapter

import com.github.charbgr.cliffhanger.domain.MiniMovie
import com.github.charbgr.cliffhanger.features.browser.adapter.BrowserAdapterItem.ViewTypes

class MovieAdapterItem(val movie: MiniMovie) : BrowserAdapterItem {
  override fun getItemViewType(): Int = ViewTypes.MOVIES
}

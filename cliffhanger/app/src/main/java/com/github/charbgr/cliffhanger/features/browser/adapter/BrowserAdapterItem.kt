package com.github.charbgr.cliffhanger.features.browser.adapter

import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.shared.adapter.BaseRvItem

interface BrowserAdapterItem : BaseRvItem {
  object ViewTypes {
    val MOVIES = R.layout.item_browser_movie
  }
}

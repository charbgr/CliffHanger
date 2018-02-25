package com.github.charbgr.cliffhanger.features.browser.adapter

import com.github.charbgr.baseadapter.BaseRvItem
import com.github.charbgr.cliffhanger.R

interface BrowserAdapterItem : BaseRvItem {
  object ViewTypes {
    val MOVIES = R.layout.item_browser_movie
  }
}

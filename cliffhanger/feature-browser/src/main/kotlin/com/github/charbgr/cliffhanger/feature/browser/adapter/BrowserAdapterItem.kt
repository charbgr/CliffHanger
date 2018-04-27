package com.github.charbgr.cliffhanger.feature.browser.adapter

import com.github.charbgr.baseadapter.BaseRvItem
import com.github.charbgr.cliffhanger.feature.browser.R

interface BrowserAdapterItem : BaseRvItem {
  object ViewTypes {
    val MOVIES = R.layout.item_browser_movie
  }
}

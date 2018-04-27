package com.github.charbgr.feature.browser.adapter

import com.github.charbgr.baseadapter.BaseRvItem
import com.github.charbgr.feature.browser.R

interface BrowserAdapterItem : BaseRvItem {
  object ViewTypes {
    val MOVIES = R.layout.item_browser_movie
  }
}

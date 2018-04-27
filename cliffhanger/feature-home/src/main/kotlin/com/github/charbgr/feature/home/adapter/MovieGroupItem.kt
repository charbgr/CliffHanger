package com.github.charbgr.feature.home.adapter

import charbgr.github.com.feature_home.R
import com.github.charbgr.baseadapter.BaseRvItem

interface MovieGroupItem : BaseRvItem {
  object ViewTypes {
    val MOVIES_CAROUSEL = R.layout.item_movies_carousel
    val SECTION = R.layout.item_section
  }
}

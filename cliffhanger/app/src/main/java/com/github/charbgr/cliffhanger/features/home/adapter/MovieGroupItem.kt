package com.github.charbgr.cliffhanger.features.home.adapter

import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.shared.adapter.BaseRvItem

interface MovieGroupItem : BaseRvItem {
  object ViewTypes {
    val MOVIES_CAROUSEL = R.layout.item_movies_carousel
    val SECTION = R.layout.item_section
  }
}

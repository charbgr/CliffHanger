package com.github.charbgr.cliffhanger.shared.adapter.movies

import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.shared.adapter.BaseRvItem

interface MovieAdapterItem : BaseRvItem {

  object ViewTypes {
    val MOVIE = R.layout.item_home_movie
  }

  fun getSpanSize(position: Int): Int
}

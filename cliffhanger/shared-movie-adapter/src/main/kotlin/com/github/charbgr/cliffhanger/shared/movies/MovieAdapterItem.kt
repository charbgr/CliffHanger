package com.github.charbgr.cliffhanger.shared.movies

import charbgr.github.com.shared_movie_adapter.R
import com.github.charbgr.baseadapter.BaseRvItem

interface MovieAdapterItem : BaseRvItem {

  object ViewTypes {
    val MOVIE = R.layout.item_home_movie
  }

  fun getSpanSize(position: Int): Int
}

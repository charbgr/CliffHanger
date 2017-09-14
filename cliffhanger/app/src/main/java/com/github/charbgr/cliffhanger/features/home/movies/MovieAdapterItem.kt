package com.github.charbgr.cliffhanger.features.home.movies

import com.github.charbgr.cliffhanger.R

interface MovieAdapterItem {

  object ViewTypes {
    val MOVIE = R.layout.item_movie
    val SECTION = R.layout.item_section
  }

  fun getItemViewType(): Int
  fun getSpanSize(position: Int): Int
}

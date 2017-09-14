package com.github.charbgr.cliffhanger.features.home.movies

interface MovieAdapterItem {
  fun getItemViewType(): Int
  fun getSpanSize(position: Int): Int
  fun getAspectRatio(position: Int): String
}

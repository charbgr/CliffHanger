package com.github.charbgr.cliffhanger.features.home.arch.state

sealed class MovieCategory {
  object TopRated : MovieCategory()
  object NowPlaying: MovieCategory()
  object Popular: MovieCategory()
  object Upcoming: MovieCategory()
  object NaN: MovieCategory()
}
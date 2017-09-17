package com.github.charbgr.cliffhanger.domain

sealed class MovieCategory {
  object TopRated : MovieCategory() {
    override fun toString(): String {
      return "TopRated"
    }
  }

  object NowPlaying: MovieCategory() {
    override fun toString(): String {
      return "NowPlaying"
    }
  }

  object Popular: MovieCategory() {
    override fun toString(): String {
      return "Popular"
    }
  }

  object Upcoming: MovieCategory() {
    override fun toString(): String {
      return "Upcoming"
    }
  }

  object NaN: MovieCategory() {
    override fun toString(): String {
      return "NaN"
    }
  }
}

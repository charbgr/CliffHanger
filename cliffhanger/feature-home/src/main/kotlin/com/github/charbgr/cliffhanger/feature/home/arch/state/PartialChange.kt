package com.github.charbgr.cliffhanger.feature.home.arch.state

import com.github.charbgr.cliffhanger.domain.MiniMovie
import com.github.charbgr.cliffhanger.domain.MovieCategory

sealed class PartialChange(val movieCategory: MovieCategory) {
  object NoOp : PartialChange(MovieCategory.NaN)
  class Loading(movieCategory: MovieCategory) : PartialChange(movieCategory)
  class Loaded(movieCategory: MovieCategory, val movies: List<MiniMovie>, val page: Int) : PartialChange(
      movieCategory)

  class Failed(movieCategory: MovieCategory, val throwable: Throwable) : PartialChange(
      movieCategory)
}

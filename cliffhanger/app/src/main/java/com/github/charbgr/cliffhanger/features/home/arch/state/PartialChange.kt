package com.github.charbgr.cliffhanger.features.home.arch.state

import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.network.tmdb.dao.MovieResults

sealed class PartialChange(val movieCategory: MovieCategory) {
  object NoOp : PartialChange(MovieCategory.NaN)
  class Loading(movieCategory: MovieCategory) : PartialChange(movieCategory)
  class Loaded(movieCategory: MovieCategory, val movieResults: MovieResults) : PartialChange(
      movieCategory)

  class Failed(movieCategory: MovieCategory, val throwable: Throwable) : PartialChange(
      movieCategory)
}

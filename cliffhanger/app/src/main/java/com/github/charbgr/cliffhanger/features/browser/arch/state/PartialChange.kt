package com.github.charbgr.cliffhanger.features.browser.arch.state

import com.github.charbgr.cliffhanger.domain.Movie

sealed class PartialChange {
  object Init : PartialChange()
  class Loading(val isInfiteScroll: Boolean) : PartialChange()
  class Loaded(val movies: List<Movie>, val page: Int) : PartialChange()
  class Failed(val throwable: Throwable) : PartialChange()
}

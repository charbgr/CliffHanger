package com.github.charbgr.cliffhanger.feature.browser.arch.state

import com.github.charbgr.cliffhanger.domain.MiniMovie

sealed class PartialChange {
  object Init : PartialChange()
  class Loading(val isInfiteScroll: Boolean) : PartialChange()
  class Loaded(val movies: List<MiniMovie>, val page: Int) : PartialChange()
  class Failed(val throwable: Throwable) : PartialChange()
}

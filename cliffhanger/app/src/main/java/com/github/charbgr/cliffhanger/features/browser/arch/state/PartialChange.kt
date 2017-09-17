package com.github.charbgr.cliffhanger.features.browser.arch.state

import com.github.charbgr.cliffhanger.network.tmdb.dao.MovieResults

sealed class PartialChange {
  object Init : PartialChange()
  class Loading : PartialChange()
  class Loaded(val movieResults: MovieResults) : PartialChange()
  class Failed(val throwable: Throwable) : PartialChange()
}

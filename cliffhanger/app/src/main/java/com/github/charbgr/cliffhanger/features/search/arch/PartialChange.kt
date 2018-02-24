package com.github.charbgr.cliffhanger.features.search.arch

import com.github.charbgr.cliffhanger.domain.SearchResults

internal sealed class PartialChange {
  object Initial : PartialChange()
  class InProgress(val fromInfiniteScroll: Boolean) : PartialChange()
  class Failed(val throwable: Throwable) : PartialChange()
  class Success(val searchResults: SearchResults, val fromInfiniteScroll: Boolean) : PartialChange()
}

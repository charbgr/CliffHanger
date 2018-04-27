package com.github.charbgr.feature.search.arch

import com.github.charbgr.cliffhanger.domain.SearchResults

internal sealed class PartialChange {
  object Initial : PartialChange()
  data class InProgress(val fromInfiniteScroll: Boolean) : PartialChange()
  data class Failed(val throwable: Throwable) : PartialChange()
  data class Success(val searchResults: SearchResults,
      val fromInfiniteScroll: Boolean) : PartialChange()
}

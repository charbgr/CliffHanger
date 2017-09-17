package com.github.charbgr.cliffhanger.features.browser.arch.state

import com.github.charbgr.cliffhanger.features.browser.arch.BrowserViewModel
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Failed
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Loaded
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Loading

class StateReducer {
  val reduce: (previousViewModel: BrowserViewModel, partialChange: PartialChange) -> BrowserViewModel =
      { previousViewModel, partialChange ->

        when (partialChange) {
          is Loading -> {
            previousViewModel.copy(isLoading = true, lastPartialChange = partialChange)
          }
          is Loaded -> {
            previousViewModel.copy(isLoading = false, movieResults = partialChange.movieResults,
                lastPartialChange = partialChange)
          }

          is Failed -> {
            previousViewModel.copy(isLoading = false, throwable = partialChange.throwable,
                lastPartialChange = partialChange)
          }
          else -> {
            previousViewModel.copy(lastPartialChange = partialChange)
          }
        }
      }
}

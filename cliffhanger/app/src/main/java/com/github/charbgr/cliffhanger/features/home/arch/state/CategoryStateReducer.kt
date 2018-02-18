package com.github.charbgr.cliffhanger.features.home.arch.state

import com.github.charbgr.cliffhanger.features.home.arch.CategoryViewModel
import com.github.charbgr.cliffhanger.features.home.arch.state.PartialChange.Failed
import com.github.charbgr.cliffhanger.features.home.arch.state.PartialChange.Loaded
import com.github.charbgr.cliffhanger.features.home.arch.state.PartialChange.Loading

class CategoryStateReducer {

  val reduce: (previousViewModel: CategoryViewModel, partialChange: PartialChange) -> CategoryViewModel =
      { previousViewModel, partialChange ->
        when (partialChange) {
          is Loading -> previousViewModel.copy(isLoading = true)
          is Loaded -> previousViewModel.copy(isLoading = false, movies = partialChange.movies)
          is Failed -> previousViewModel.copy(isLoading = false, error = partialChange.throwable)
          else -> previousViewModel
        }
      }
}

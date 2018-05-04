package com.github.charbgr.cliffhanger.feature.home.arch

import com.github.charbgr.cliffhanger.feature.home.arch.state.PartialChange

data class HomeViewModel(
  val topRated: CategoryViewModel,
  val nowPlaying: CategoryViewModel,
  val popular: CategoryViewModel,
  val upcoming: CategoryViewModel,
  val currentPartialChange: PartialChange
) {

  companion object {
    fun initial(): HomeViewModel {
      return HomeViewModel(
          topRated = CategoryViewModel.initial(),
          nowPlaying = CategoryViewModel.initial(),
          popular = CategoryViewModel.initial(),
          upcoming = CategoryViewModel.initial(),
          currentPartialChange = PartialChange.NoOp
      )
    }
  }
}

package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.arch.BaseStateReducer
import com.github.charbgr.cliffhanger.features.detail.arch.PartialChange.Failed
import com.github.charbgr.cliffhanger.features.detail.arch.PartialChange.InProgress
import com.github.charbgr.cliffhanger.features.detail.arch.PartialChange.Initial
import com.github.charbgr.cliffhanger.features.detail.arch.PartialChange.Success

internal class StateReducer: BaseStateReducer<PartialChange, ViewModel>() {

  val initial: Pair<PartialChange, ViewModel> by lazy {
    Pair(PartialChange.Initial, ViewModel.Initial())
  }

  override fun reduceState(previousPartialChange: PartialChange, previousViewModel: ViewModel,
      partialChange: PartialChange): ViewModel {

    return when (partialChange) {
      Initial -> { ViewModel.Initial() }
      InProgress -> {
        previousViewModel.copy(showError = false, showLoader = true)
      }
      is Failed -> {
        previousViewModel.copy(
            showError = true,
            throwable = partialChange.throwable,
            showLoader = false,
            showMovie = false
        )
      }
      is Success -> {
        previousViewModel.copy(
            showError = false,
            throwable = null,
            showLoader = false,
            showMovie = true,
            movie = partialChange.movie
        )
      }
    }
  }

}

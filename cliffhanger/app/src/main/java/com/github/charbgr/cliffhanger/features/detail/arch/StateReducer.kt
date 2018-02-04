package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.features.detail.arch.PartialChange.Failed
import com.github.charbgr.cliffhanger.features.detail.arch.PartialChange.InProgress
import com.github.charbgr.cliffhanger.features.detail.arch.PartialChange.Initial
import com.github.charbgr.cliffhanger.features.detail.arch.PartialChange.Success
import io.reactivex.functions.BiFunction

internal class StateReducer {

  val initial: Pair<PartialChange, ViewModel> by lazy {
    Pair(PartialChange.Initial, ViewModel.Initial())
  }

  val reduce = BiFunction<Pair<PartialChange, ViewModel>, PartialChange, Pair<PartialChange, ViewModel>>
  { previousState, partialChange ->

    val previousViewModel = previousState.second
    val toViewModel: ViewModel = when (partialChange) {
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

    Pair(partialChange, toViewModel)
  }
}

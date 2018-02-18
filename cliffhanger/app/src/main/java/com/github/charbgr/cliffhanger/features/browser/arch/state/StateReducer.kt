package com.github.charbgr.cliffhanger.features.browser.arch.state

import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.features.browser.arch.BrowserViewModel
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Failed
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Loaded
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Loading
import io.reactivex.functions.BiFunction

internal class StateReducer {

  fun initState(movieCategory: MovieCategory) =
      Pair(PartialChange.Init, BrowserViewModel.initial(movieCategory))

  val reduce = BiFunction<Pair<PartialChange, BrowserViewModel>, PartialChange, Pair<PartialChange, BrowserViewModel>>
  { previousState, partialChange ->
    val previousViewModel = previousState.second
    val toViewModel: BrowserViewModel = when (partialChange) {
      is Loading -> previousViewModel.copy(isLoading = true)
      is Loaded -> {
        previousViewModel.copy(isLoading = false, movies = partialChange.movies,
            page = partialChange.page)
      }

      is Failed -> previousViewModel.copy(isLoading = false, throwable = partialChange.throwable)
      else -> previousViewModel
    }

    Pair(partialChange, toViewModel)
  }
}

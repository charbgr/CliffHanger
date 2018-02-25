package com.github.charbgr.cliffhanger.features.search.arch


import com.github.charbgr.cliffhanger.domain.MiniMovie
import com.github.charbgr.cliffhanger.features.search.arch.PartialChange.Failed
import com.github.charbgr.cliffhanger.features.search.arch.PartialChange.InProgress
import com.github.charbgr.cliffhanger.features.search.arch.PartialChange.Initial
import com.github.charbgr.cliffhanger.features.search.arch.PartialChange.Success
import com.github.charbgr.arch.BaseStateReducer

internal class StateReducer : BaseStateReducer<PartialChange, ViewModel>() {

  val initial: Pair<PartialChange, ViewModel> by lazy {
    Pair(PartialChange.Initial, ViewModel.Initial())
  }

  override fun reduceState(previousPartialChange: PartialChange, previousViewModel: ViewModel,
      partialChange: PartialChange): ViewModel {

    return when (partialChange) {
      Initial -> {
        ViewModel.Initial()
      }
      is InProgress -> {
        var movies: List<MiniMovie>? = previousViewModel.movies
        if (!partialChange.fromInfiniteScroll) {
          movies = null
        }

        previousViewModel.copy(showError = false, showLoader = !partialChange.fromInfiniteScroll, movies = movies)
      }
      is Failed -> {
        previousViewModel.copy(
            showError = true,
            throwable = partialChange.throwable,
            showLoader = false,
            showMovies = false
        )
      }
      is Success -> {

        val movies: MutableList<MiniMovie> = previousViewModel.movies?.toMutableList()
            ?: mutableListOf()
        movies.addAll(partialChange.searchResults.movies)

        previousViewModel.copy(
            showError = false,
            throwable = null,
            showLoader = false,
            showMovies = true,
            movies = movies
        )
      }
    }
  }

}

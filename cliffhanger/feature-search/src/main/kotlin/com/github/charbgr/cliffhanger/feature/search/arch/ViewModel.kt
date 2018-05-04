package com.github.charbgr.cliffhanger.feature.search.arch

import com.github.charbgr.cliffhanger.domain.MiniMovie

data class ViewModel(
  val showLoader: Boolean,
  val showMovies: Boolean,
  val showError: Boolean,
  val throwable: Throwable? = null,
  val movies: List<MiniMovie>? = null
) {

  companion object {
    fun Initial() = ViewModel(
        showLoader = false,
        showMovies = false,
        showError = false,
        movies = null
    )
  }
}

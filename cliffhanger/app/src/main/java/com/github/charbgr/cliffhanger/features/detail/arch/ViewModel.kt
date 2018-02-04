package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.domain.Movie

internal data class ViewModel(
    val showLoader: Boolean,
    val showMovie: Boolean,
    val showError: Boolean,
    val throwable: Throwable? = null,
    val movie: Movie? = null
) {

  companion object {
    fun Initial() = ViewModel(
        showLoader = false,
        showMovie = false,
        showError = false,
        movie = null
    )
  }
}

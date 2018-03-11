package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.domain.FullMovie

internal data class ViewModel(
    val showLoader: Boolean,
    val showMovie: Boolean,
    val showError: Boolean,
    val throwable: Throwable? = null,
    val movie: FullMovie? = null
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

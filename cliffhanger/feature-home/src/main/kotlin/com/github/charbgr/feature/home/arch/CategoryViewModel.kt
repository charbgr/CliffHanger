package com.github.charbgr.feature.home.arch

import com.github.charbgr.cliffhanger.domain.MiniMovie

data class CategoryViewModel(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val movies: List<MiniMovie>? = null
) {


  companion object {
    fun initial(): CategoryViewModel {
      return CategoryViewModel(isLoading = false, error = null, movies = null)
    }
  }


  fun hasError(): Boolean = error != null
  fun hasData(): Boolean = movies != null

}

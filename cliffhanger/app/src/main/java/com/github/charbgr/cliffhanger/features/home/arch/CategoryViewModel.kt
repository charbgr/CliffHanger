package com.github.charbgr.cliffhanger.features.home.arch

import com.github.charbgr.cliffhanger.network.tmdb.dao.MovieResults

data class CategoryViewModel(
    val isLoading: Boolean = false,
    val error: Throwable? = null,
    val movieResults: MovieResults? = null
) {


  companion object {
    fun initial(): CategoryViewModel {
      return CategoryViewModel(isLoading = false, error = null, movieResults = null)
    }
  }


  fun hasError(): Boolean = error != null
  fun hasData(): Boolean = movieResults != null

}
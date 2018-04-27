package com.github.charbgr.cliffhanger.api_tmdb

object TmdbHelper {
  fun bestPoster(posterPath: String): String {
    return Routes.IMAGE_BASE_URL + "w185" + posterPath
  }

  fun bestBackdrop(backdropPath: String): String {
    return Routes.IMAGE_BASE_URL + "w500" + backdropPath
  }
}

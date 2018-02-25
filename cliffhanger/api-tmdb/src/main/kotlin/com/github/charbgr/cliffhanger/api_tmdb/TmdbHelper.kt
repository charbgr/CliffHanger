package com.github.charbgr.cliffhanger.api_tmdb

object TmdbHelper {
  fun findBestQualityPoster(posterPath: String): String {
    return Routes.IMAGE_BASE_URL + "w185" + posterPath
  }

  fun findBestQualityBackdrop(backdropPath: String): String {
    return Routes.IMAGE_BASE_URL + "w500" + backdropPath
  }
}

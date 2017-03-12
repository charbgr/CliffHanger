package com.github.charbgr.cliffhanger.tmdb

import com.github.charbgr.cliffhanger.Movie

object TmdbHelper {
  fun findBestQualityPoster(movie: Movie): String {
    return Routes.IMAGE_BASE_URL + "w185" + movie.posterPath
  }

  fun findBestQualityBackdrop(movie: Movie): String {
    return Routes.IMAGE_BASE_URL + "w500" + movie.backdropPath
  }
}
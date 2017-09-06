package com.github.charbgr.cliffhanger.network.tmdb

import com.github.charbgr.cliffhanger.domain.Movie

object TmdbHelper {
  fun findBestQualityPoster(movie: Movie): String {
    return Routes.IMAGE_BASE_URL + "w185" + movie.posterPath
  }

  fun findBestQualityBackdrop(movie: Movie): String {
    return Routes.IMAGE_BASE_URL + "w500" + movie.backdropPath
  }
}
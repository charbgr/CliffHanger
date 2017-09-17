package com.github.charbgr.cliffhanger.network.tmdb.dao

import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.network.tmdb.dto.MiniMovieDto
import com.github.charbgr.cliffhanger.shared.transformers.movie.transformToMovies
import com.squareup.moshi.Json

data class MovieResults(
  @Json(name = "page")
  val page: Int,

  @Json(name = "results")
  val results: List<MiniMovieDto>,

  @Json(name = "total_results")
  val totalResults: Int,

  @Json(name = "total_pages")
  val totalPages: Int
) {


  fun toMovieList(): List<Movie> {
    return results.transformToMovies()
  }
}

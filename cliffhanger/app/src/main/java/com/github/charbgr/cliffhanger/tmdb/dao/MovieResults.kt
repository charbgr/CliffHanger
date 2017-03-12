package com.github.charbgr.cliffhanger.tmdb.dao

import com.github.charbgr.cliffhanger.tmdb.dto.MiniMovieDto
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
)
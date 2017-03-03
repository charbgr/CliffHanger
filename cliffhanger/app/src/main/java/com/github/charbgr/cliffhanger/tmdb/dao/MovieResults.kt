package com.github.charbgr.cliffhanger.tmdb.dao

import com.github.charbgr.cliffhanger.tmdb.dto.MiniMovieDto
import com.squareup.moshi.Json

data class MovieResults(
  @Json(name = "page")
  private val page: Int,

  @Json(name = "results")
  private val results: List<MiniMovieDto>,

  @Json(name = "total_results")
  private val totalResults: Int,

  @Json(name = "total_pages")
  private val totalPages: Int
)
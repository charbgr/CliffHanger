package com.github.charbgr.cliffhanger.api_tmdb.entity

import com.squareup.moshi.Json

data class FullMovieEntity(

  @Json(name = "id")
  val id: Int?,

  @Json(name = "poster_path")
  val posterPath: String?,

  @Json(name = "title")
  val title: String?,

  @Json(name = "backdrop_path")
  val backdropPath: String?,

  @Json(name = "overview")
  val overview: String?,

  @Json(name = "tagline")
  val tagline: String?,

  @Json(name = "runtime")
  val runtime: Int?,

  @Json(name = "release_date")
  val releaseDate: String?,

  @Json(name = "credits")
  val credits: CreditsEntity?

)

package com.github.charbgr.cliffhanger.tmdb.dto

import com.github.charbgr.cliffhanger.shared.transformers.movie.MovieTransformable
import com.squareup.moshi.Json

data class MiniMovieDto(

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "adult")
    val adult: Boolean?,

    @Json(name = "overview")
    val overview: String?,

    @Json(name = "release_date")
    val releaseDate: String?,

    @Json(name = "genre_ids")
    val genreIds: List<Int>?,

    @Json(name = "id")
    val id: Int?,

    @Json(name = "original_title")
    val originalTitle: String?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "vote_count")
    val voteCount: Int?,

    @Json(name = "vote_average")
    val voteAverage: Double?

) : MovieTransformable {
  override fun tmdbId(): Int? = this.id
  override fun title(): String? = this.title
}


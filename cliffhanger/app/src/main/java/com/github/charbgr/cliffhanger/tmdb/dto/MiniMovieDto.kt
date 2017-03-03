package com.github.charbgr.cliffhanger.tmdb.dto

import com.squareup.moshi.Json

data class MiniMovieDto(

    @Json(name = "poster_path")
    private val posterPath: String,

    @Json(name = "adult")
    private val adult: Boolean,

    @Json(name = "overview")
    private val overview: String,

    @Json(name = "release_date")
    private val releaseDate: String,

    @Json(name = "genre_ids")
    private val genreIds: List<Int>,

    @Json(name = "id")
    private val id: Int,

    @Json(name = "original_title")
    private val originalTitle: String,

    @Json(name = "title")
    private val title: String,

    @Json(name = "backdrop_path")
    private val backdropPath: String,

    @Json(name = "vote_count")
    private val voteCount: Int,

    @Json(name = "vote_average")
    private val voteAverage: Double
)

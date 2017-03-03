package com.github.charbgr.cliffhanger.tmdb.dto

import com.squareup.moshi.Json

data class MovieDto(
    @Json(name = "id")
    private val id: Int,


    @Json(name = "adult")
    private val adult: Boolean,

    @Json(name = "backdrop_path")
    private val backdropPath: String,

    @Json(name = "budget")
    private val budget: Int,

    //@Json(name ="genre")
//private val List<Genre> genres,

    @Json(name = "homepage")
    private val homepage: String,

    @Json(name = "imdb_id")
    private val imdbId: String,

    @Json(name = "original_title")
    private val originalTitle: String,

    @Json(name = "overview")
    private val overview: String,

    @Json(name = "popularity")
    private val popularity: Double,

    @Json(name = "poster_path")
    private val posterPath: String,

    @Json(name = "release_date")
    private val releaseDate: String,

    @Json(name = "revenue")
    private val revenue: Int,

    @Json(name = "runtime")
    private val runtime: Int,

    @Json(name = "tagline")
    private val tagline: String,

    @Json(name = "title")
    private val title: String,

    @Json(name = "vote_average")
    private val voteAverage: Double,

    @Json(name = "vote_count")
    private val voteCount: Int,

    @Json(name = "status")
    private val status: String
)

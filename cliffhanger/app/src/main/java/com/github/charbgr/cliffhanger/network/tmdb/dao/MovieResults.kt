package com.github.charbgr.cliffhanger.network.tmdb.dao

import com.github.charbgr.cliffhanger.network.tmdb.entity.MiniMovieEntity
import com.squareup.moshi.Json

data class MovieResults(
    @Json(name = "page")
    val page: Int,

    @Json(name = "results")
    val results: List<MiniMovieEntity>,

    @Json(name = "total_results")
    val totalResults: Int,

    @Json(name = "total_pages")
    val totalPages: Int
)

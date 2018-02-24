package com.github.charbgr.cliffhanger.network.tmdb.entity

import com.squareup.moshi.Json

data class SearchResultsEntity(

    @Json(name = "page")
    val page: Int,

    @Json(name = "results")
    val results: List<MiniMovieEntity>

)

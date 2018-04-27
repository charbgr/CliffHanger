package com.github.charbgr.cliffhanger.api_tmdb.entity

import com.squareup.moshi.Json

data class MiniMovieEntity(

    @Json(name = "id")
    val id: Int?,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "title")
    val title: String?,

    @Json(name = "backdrop_path")
    val backdropPath: String?

)


package com.github.charbgr.cliffhanger.network.tmdb.entity

import com.squareup.moshi.Json

data class CreditsEntity(

    @Json(name = "crew")
    val crewEntities: List<CrewEntity>

)

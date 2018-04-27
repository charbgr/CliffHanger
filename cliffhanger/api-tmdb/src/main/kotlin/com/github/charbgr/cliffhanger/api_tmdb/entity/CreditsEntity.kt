package com.github.charbgr.cliffhanger.api_tmdb.entity

import com.squareup.moshi.Json

data class CreditsEntity(

    @Json(name = "crew")
    val crewEntities: List<CrewEntity>

)

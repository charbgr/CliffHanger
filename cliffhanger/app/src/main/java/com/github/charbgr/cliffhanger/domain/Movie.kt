package com.github.charbgr.cliffhanger.domain

data class Movie(
    val tmdbId: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String)
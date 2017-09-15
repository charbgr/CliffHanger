package com.github.charbgr.cliffhanger.features.home.arch

import com.github.charbgr.cliffhanger.network.tmdb.dao.MovieResults

data class HomeViewModel(
    val loading: Boolean = false,
    val topRated: MovieResults? = null,
    val nowPlaying: MovieResults? = null,
    val popular: MovieResults? = null,
    val upcoming: MovieResults? = null
)
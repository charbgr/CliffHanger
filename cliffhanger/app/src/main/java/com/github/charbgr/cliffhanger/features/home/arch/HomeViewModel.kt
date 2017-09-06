package com.github.charbgr.cliffhanger.features.home.arch

import com.github.charbgr.cliffhanger.network.tmdb.dao.MovieResults

data class HomeViewModel(
    val loading: Boolean = false,
    val movieResults: MovieResults? = null
)
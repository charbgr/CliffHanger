package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.tmdb.dao.MovieResults

data class HomeViewModel(
    val loading: Boolean = false,
    val movieResults: MovieResults? = null
)
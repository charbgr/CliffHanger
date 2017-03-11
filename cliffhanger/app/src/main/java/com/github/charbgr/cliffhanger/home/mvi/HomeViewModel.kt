package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.Movie

data class HomeViewModel(
    val loading: Boolean = false,
    val fetched: Boolean = false,
    val movie: Movie? = null
)
package com.github.charbgr.cliffhanger.domain

data class SearchResults(
    val page: Int,
    val movies: List<MiniMovie>
)

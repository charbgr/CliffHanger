package com.github.charbgr.cliffhanger.api_tmdb.entity

import com.github.charbgr.cliffhanger.domain.MiniMovie

object MiniMovieEntityMapper {

  fun transform(entity: MiniMovieEntity): MiniMovie {
    return MiniMovie(
        tmdbId = entity.id!!,
        title = entity.title ?: "",
        posterPath = entity.posterPath ?: "",
        backdropPath = entity.backdropPath ?: ""
    )
  }

  fun transform(entities: List<MiniMovieEntity>): List<MiniMovie> = entities.map { transform(it) }
}

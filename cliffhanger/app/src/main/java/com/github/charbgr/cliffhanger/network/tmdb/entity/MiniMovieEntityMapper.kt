package com.github.charbgr.cliffhanger.network.tmdb.entity

import com.github.charbgr.cliffhanger.domain.MiniMovie
import com.github.charbgr.cliffhanger.shared.extensions.empty

class MiniMovieEntityMapper {

  fun transform(entity: MiniMovieEntity): MiniMovie {
    return MiniMovie(
        tmdbId = entity.id!!,
        title = entity.title ?: String.empty(),
        posterPath = entity.posterPath ?: String.empty(),
        backdropPath = entity.backdropPath ?: String.empty()
    )
  }

  fun transform(entities: List<MiniMovieEntity>): List<MiniMovie> = entities.map { transform(it) }
}


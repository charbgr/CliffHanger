package com.github.charbgr.cliffhanger.network.tmdb.entity

import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.shared.extensions.empty

class MiniMovieEntityMapper {

  fun transform(entity: MiniMovieEntity): Movie {
    return Movie(
        tmdbId = entity.id!!,
        title = entity.title ?: String.empty(),
        posterPath = entity.posterPath ?: String.empty(),
        backdropPath = entity.backdropPath ?: String.empty()
    )
  }

  fun transform(entities: List<MiniMovieEntity>): List<Movie> = entities.map { transform(it) }
}


package com.github.charbgr.cliffhanger.api_tmdb.entity

import com.github.charbgr.cliffhanger.domain.FullMovie

object FullMovieEntityMapper {

  fun transform(entity: FullMovieEntity): FullMovie {
    return FullMovie(
        tmdbId = entity.id ?: 0,
        title = entity.title ?: "",
        posterPath = entity.posterPath ?: "",
        backdropPath = entity.backdropPath ?: "",
        overview = entity.overview ?: "",
        tagline = entity.tagline,
        crewMembers = CrewEntityMapper.transform(entity.credits?.crewEntities),
        duration = entity.runtime ?: 0,
        releaseDate = entity.releaseDate ?: ""
    )
  }
}

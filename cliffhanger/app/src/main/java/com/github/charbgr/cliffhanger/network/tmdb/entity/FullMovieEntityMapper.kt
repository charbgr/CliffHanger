package com.github.charbgr.cliffhanger.network.tmdb.entity

import com.github.charbgr.cliffhanger.domain.FullMovie
import com.github.charbgr.cliffhanger.shared.extensions.empty

object FullMovieEntityMapper {


  fun transform(entity: FullMovieEntity): FullMovie {
    return FullMovie(
        tmdbId = entity.id ?: 0,
        title = entity.title ?: String.empty(),
        posterPath = entity.posterPath ?: String.empty(),
        backdropPath = entity.backdropPath ?: String.empty(),
        overview = entity.overview ?: String.empty(),
        tagline = entity.tagline,
        crewMembers = CrewEntityMapper.transform(entity.credits?.crewEntities),
        duration = entity.runtime ?: 0,
        releaseDate = entity.releaseDate ?: String.empty()
    )
  }

}


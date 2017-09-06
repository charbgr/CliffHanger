package com.github.charbgr.cliffhanger.shared.transformers.movie

import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.shared.extensions.hasAnyNullValues
import com.github.charbgr.cliffhanger.shared.transformers.TransformationException
import com.github.charbgr.cliffhanger.shared.transformers.Transformer
import com.github.charbgr.cliffhanger.network.tmdb.dto.MiniMovieDto

class MovieTransformer(private val transformable: MovieTransformable) : Transformer<Movie> {

  override fun transform(): Movie {
    listOf(
        transformable.tmdbId(),
        transformable.title(),
        transformable.posterPath(),
        transformable.backdropPath()
    ).hasAnyNullValues({ throw TransformationException(transformable, Movie::class) })

    return Movie(
        tmdbId = transformable.tmdbId()!!,
        title = transformable.title()!!,
        posterPath = transformable.posterPath()!!,
        backdropPath = transformable.backdropPath()!!)
  }
}

fun List<MiniMovieDto>.transformToMovies(): List<Movie> {
  val movieList: MutableList<Movie> = mutableListOf()

  this.forEach {
    try {
      movieList.add(MovieTransformer(it).transform())
    } catch (ignore: TransformationException) {

    }

  }

  return movieList
}
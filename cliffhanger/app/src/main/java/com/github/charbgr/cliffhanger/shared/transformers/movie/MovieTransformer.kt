package com.github.charbgr.cliffhanger.shared.transformers.movie

import com.github.charbgr.cliffhanger.Movie
import com.github.charbgr.cliffhanger.shared.extensions.hasAnyNullValues
import com.github.charbgr.cliffhanger.shared.transformers.TransformationException
import com.github.charbgr.cliffhanger.shared.transformers.Transformer

class MovieTransformer(private val transformable: MovieTransformable) : Transformer<Movie> {

  override fun transform(): Movie {
    listOf(
        transformable.tmdbId(),
        transformable.title()
    ).hasAnyNullValues({ throw TransformationException(transformable, Movie::class) })

    return Movie(
        tmdbId = transformable.tmdbId()!!,
        title = transformable.title()!!)
  }

}
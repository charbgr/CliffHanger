package com.github.charbgr.cliffhanger.shared.transformers.movie

/**
 * With this interface, we transform various object to [Movie] object.
 */
interface MovieTransformable {
  fun tmdbId(): Int?
  fun title(): String?
}
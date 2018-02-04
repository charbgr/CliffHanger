package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.network.tmdb.TmdbAPI
import io.reactivex.Single

interface MovieRepository {
  fun getMovie(movieId: Int): Single<Movie>

  class Network(private val api: TmdbAPI) : MovieRepository {
    override fun getMovie(movieId: Int): Single<Movie> {
      return api.movieDAO.getMovie(movieId)
    }
  }

}

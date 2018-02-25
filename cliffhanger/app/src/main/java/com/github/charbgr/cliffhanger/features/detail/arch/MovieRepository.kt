package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.api_tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.api_tmdb.entity.FullMovieEntity
import com.github.charbgr.cliffhanger.api_tmdb.entity.SearchResultsEntity
import io.reactivex.Single

interface MovieRepository {
  fun getMovie(movieId: Int): Single<FullMovieEntity>
  fun searchMovies(query: CharSequence, page: Int): Single<SearchResultsEntity>

  class Network(private val api: TmdbAPI) : MovieRepository {
    override fun getMovie(movieId: Int): Single<FullMovieEntity> {
      return api.movieDAO.getMovie(movieId)
    }

    override fun searchMovies(query: CharSequence, page: Int): Single<SearchResultsEntity> {
      return api.movieDAO.searchMovie(query.toString(), page)
    }
  }

}

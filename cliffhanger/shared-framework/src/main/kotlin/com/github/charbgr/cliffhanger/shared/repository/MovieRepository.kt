package com.github.charbgr.cliffhanger.shared.repository

import com.github.charbgr.cliffhanger.api_tmdb.dao.MovieResults
import com.github.charbgr.cliffhanger.api_tmdb.entity.FullMovieEntity
import com.github.charbgr.cliffhanger.api_tmdb.entity.SearchResultsEntity
import io.reactivex.Single

interface MovieRepository {
  fun getMovie(movieId: Int): Single<FullMovieEntity>
  fun searchMovies(query: CharSequence, page: Int): Single<SearchResultsEntity>

  fun fetchPopularMovies(page: Int): Single<MovieResults>
  fun fetchTopRatedMovies(page: Int): Single<MovieResults>
  fun fetchUpcomingMovies(page: Int): Single<MovieResults>
  fun fetchNowPlayingMovies(page: Int): Single<MovieResults>
}

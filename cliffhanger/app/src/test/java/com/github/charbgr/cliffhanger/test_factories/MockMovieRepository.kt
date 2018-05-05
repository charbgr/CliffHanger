package com.github.charbgr.cliffhanger.test_factories

import com.github.charbgr.cliffhanger.api_tmdb.dao.MovieResults
import com.github.charbgr.cliffhanger.api_tmdb.entity.FullMovieEntity
import com.github.charbgr.cliffhanger.api_tmdb.entity.SearchResultsEntity
import com.github.charbgr.cliffhanger.shared.repository.MovieRepository
import io.reactivex.Single

class MockMovieRepository(
  val movie: FullMovieEntity? = null,
  val searchResultsEntity: SearchResultsEntity? = null
) : MovieRepository {

  override fun getMovie(movieId: Int): Single<FullMovieEntity> {
    return Single.just(movie)
  }

  override fun searchMovies(query: CharSequence, page: Int): Single<SearchResultsEntity> {
    return Single.just(searchResultsEntity)
  }

  override fun fetchPopularMovies(page: Int): Single<MovieResults> = Single.never()
  override fun fetchTopRatedMovies(page: Int): Single<MovieResults> = Single.never()
  override fun fetchUpcomingMovies(page: Int): Single<MovieResults> = Single.never()
  override fun fetchNowPlayingMovies(page: Int): Single<MovieResults> = Single.never()
}

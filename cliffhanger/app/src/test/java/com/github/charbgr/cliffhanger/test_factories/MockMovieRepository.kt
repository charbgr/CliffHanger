package com.github.charbgr.cliffhanger.test_factories

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
}

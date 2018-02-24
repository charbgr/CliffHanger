package com.github.charbgr.cliffhanger.test_factories

import com.github.charbgr.cliffhanger.features.detail.arch.MovieRepository
import com.github.charbgr.cliffhanger.network.tmdb.entity.FullMovieEntity
import com.github.charbgr.cliffhanger.network.tmdb.entity.SearchResultsEntity
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

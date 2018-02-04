package com.github.charbgr.cliffhanger.test_factories

import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.features.detail.arch.MovieRepository
import io.reactivex.Single

class MockMovieRepository(val movie: Movie) : MovieRepository {

  override fun getMovie(movieId: Int): Single<Movie> {
    return Single.just(movie)
  }

}

package com.github.charbgr.cliffhanger.test_factories

import com.github.charbgr.cliffhanger.features.detail.arch.MovieRepository
import com.github.charbgr.cliffhanger.network.tmdb.entity.FullMovieEntity
import io.reactivex.Single

class MockMovieRepository(val movie: FullMovieEntity) : MovieRepository {

  override fun getMovie(movieId: Int): Single<FullMovieEntity> {
    return Single.just(movie)
  }

}

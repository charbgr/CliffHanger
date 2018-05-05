package com.github.charbgr.cliffhanger.feature.home.arch

import com.github.charbgr.cliffhanger.api_tmdb.dao.MovieResults
import com.github.charbgr.cliffhanger.api_tmdb.entity.MiniMovieEntityMapper.transform
import com.github.charbgr.cliffhanger.di.Deppie
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.domain.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.domain.MovieCategory.Popular
import com.github.charbgr.cliffhanger.domain.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.domain.MovieCategory.Upcoming
import com.github.charbgr.cliffhanger.feature.home.arch.state.PartialChange
import com.github.charbgr.cliffhanger.feature.home.arch.state.PartialChange.Failed
import com.github.charbgr.cliffhanger.feature.home.arch.state.PartialChange.Loaded
import com.github.charbgr.cliffhanger.feature.home.arch.state.PartialChange.Loading
import com.github.charbgr.cliffhanger.shared.repository.MovieRepository
import io.reactivex.Observable

class DefaultHomeInteractor : HomeInteractor {

  private val movieRepository: MovieRepository = Deppie.getInstance().movieRepository

  override fun loadTopRatedMovies(): Observable<PartialChange> {
    return movieRepository.fetchTopRatedMovies(1)
      .toObservable()
      .map { Loaded(TopRated, transform(it.results), it.page) as PartialChange }
      .startWith(Loading(TopRated))
      .onErrorReturn { Failed(TopRated, it) }
      .share()
  }

  override fun loadNowPlayingMovies(): Observable<PartialChange> {
    return movieRepository.fetchNowPlayingMovies(1)
      .toObservable()
      .map { Loaded(NowPlaying, transform(it.results), it.page) as PartialChange }
      .startWith(Loading(NowPlaying))
      .onErrorReturn { Failed(NowPlaying, it) }
      .share()
  }

  override fun loadWatchlistMovies(): Observable<MovieResults> {
    return Observable.empty()
  }

  override fun loadPopularMovies(): Observable<PartialChange> {
    return movieRepository.fetchPopularMovies(1)
      .toObservable()
      .map { Loaded(Popular, transform(it.results), it.page) as PartialChange }
      .startWith(Loading(Popular))
      .onErrorReturn { Failed(MovieCategory.Popular, it) }
      .share()
  }

  override fun loadUpcomingMovies(): Observable<PartialChange> {
    return movieRepository.fetchUpcomingMovies(1)
      .toObservable()
      .map { Loaded(Upcoming, transform(it.results), it.page) as PartialChange }
      .startWith(Loading(Upcoming))
      .onErrorReturn { Failed(Upcoming, it) }
      .share()
  }
}

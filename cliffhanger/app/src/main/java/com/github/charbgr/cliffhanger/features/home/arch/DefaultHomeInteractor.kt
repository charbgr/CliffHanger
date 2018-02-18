package com.github.charbgr.cliffhanger.features.home.arch

import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.domain.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.domain.MovieCategory.Popular
import com.github.charbgr.cliffhanger.domain.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.domain.MovieCategory.Upcoming
import com.github.charbgr.cliffhanger.features.home.arch.state.PartialChange
import com.github.charbgr.cliffhanger.features.home.arch.state.PartialChange.Failed
import com.github.charbgr.cliffhanger.features.home.arch.state.PartialChange.Loaded
import com.github.charbgr.cliffhanger.features.home.arch.state.PartialChange.Loading
import com.github.charbgr.cliffhanger.network.tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.network.tmdb.dao.MovieResults
import com.github.charbgr.cliffhanger.network.tmdb.entity.MiniMovieEntityMapper
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DefaultHomeInteractor : HomeInteractor {

  private val tmdbAPI: TmdbAPI = TmdbAPI.create(Schedulers.io())
  private val mapper: MiniMovieEntityMapper = MiniMovieEntityMapper()

  override fun loadTopRatedMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.topRatedMovies(1)
        .map { Loaded(TopRated, mapper.transform(it.results), it.page) as PartialChange }
        .startWith(Loading(TopRated))
        .onErrorReturn { Failed(TopRated, it) }
        .share()
  }

  override fun loadNowPlayingMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.nowPlayingMovie(1)
        .map { Loaded(NowPlaying, mapper.transform(it.results), it.page) as PartialChange }
        .startWith(Loading(NowPlaying))
        .onErrorReturn { Failed(NowPlaying, it) }
        .share()
  }

  override fun loadWatchlistMovies(): Observable<MovieResults> {
    return Observable.empty()
  }

  override fun loadPopularMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.popularMovies(1)
        .map { Loaded(Popular, mapper.transform(it.results), it.page) as PartialChange }
        .startWith(Loading(Popular))
        .onErrorReturn { Failed(MovieCategory.Popular, it) }
        .share()
  }

  override fun loadUpcomingMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.upcomingMovies(1)
        .map { Loaded(Upcoming, mapper.transform(it.results), it.page) as PartialChange }
        .startWith(Loading(Upcoming))
        .onErrorReturn { Failed(Upcoming, it) }
        .share()
  }

}

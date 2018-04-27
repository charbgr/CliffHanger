package com.github.charbgr.feature.home.arch

import com.github.charbgr.cliffhanger.api_tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.api_tmdb.dao.MovieResults
import com.github.charbgr.cliffhanger.api_tmdb.entity.MiniMovieEntityMapper.transform
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.domain.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.domain.MovieCategory.Popular
import com.github.charbgr.cliffhanger.domain.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.domain.MovieCategory.Upcoming
import com.github.charbgr.feature.home.arch.state.PartialChange
import com.github.charbgr.feature.home.arch.state.PartialChange.Failed
import com.github.charbgr.feature.home.arch.state.PartialChange.Loaded
import com.github.charbgr.feature.home.arch.state.PartialChange.Loading
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DefaultHomeInteractor : HomeInteractor {

  private val tmdbAPI: TmdbAPI = TmdbAPI(Schedulers.io())

  override fun loadTopRatedMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.topRatedMovies(1)
        .map { Loaded(TopRated, transform(it.results), it.page) as PartialChange }
        .startWith(Loading(TopRated))
        .onErrorReturn { Failed(TopRated, it) }
        .share()
  }

  override fun loadNowPlayingMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.nowPlayingMovie(1)
        .map { Loaded(NowPlaying, transform(it.results), it.page) as PartialChange }
        .startWith(Loading(NowPlaying))
        .onErrorReturn { Failed(NowPlaying, it) }
        .share()
  }

  override fun loadWatchlistMovies(): Observable<MovieResults> {
    return Observable.empty()
  }

  override fun loadPopularMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.popularMovies(1)
        .map { Loaded(Popular, transform(it.results), it.page) as PartialChange }
        .startWith(Loading(Popular))
        .onErrorReturn { Failed(MovieCategory.Popular, it) }
        .share()
  }

  override fun loadUpcomingMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.upcomingMovies(1)
        .map { Loaded(Upcoming, transform(it.results), it.page) as PartialChange }
        .startWith(Loading(Upcoming))
        .onErrorReturn { Failed(Upcoming, it) }
        .share()
  }

}

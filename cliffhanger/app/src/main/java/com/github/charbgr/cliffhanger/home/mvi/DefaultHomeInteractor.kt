package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.tmdb.dao.MovieResults
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DefaultHomeInteractor : HomeInteractor {

  private val tmdbAPI: TmdbAPI = TmdbAPI.create(Schedulers.io())

  override fun loadTopRatedMovies(): Observable<MovieResults> {
    return tmdbAPI.movieDAO.topRatedMovies().share()
  }

  override fun loadNowPlayingMovies(): Observable<MovieResults> {
    return tmdbAPI.movieDAO.nowPlayingMovie().share()
  }

  override fun loadWatchlistMovies(): Observable<MovieResults> {
    return Observable.empty()
  }

  override fun loadPopularMovies(): Observable<MovieResults> {
    return tmdbAPI.movieDAO.popularMovies().share()
  }

  override fun loadUpcomingMovies(): Observable<MovieResults> {
    return tmdbAPI.movieDAO.upcomingMovies().share()
  }

}

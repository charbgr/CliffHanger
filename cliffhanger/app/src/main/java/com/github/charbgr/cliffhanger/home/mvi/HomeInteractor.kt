package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.tmdb.dao.MovieResults
import io.reactivex.Observable

interface HomeInteractor {
  fun loadTopRatedMovies(): Observable<MovieResults>
  fun loadNowPlayingMovies(): Observable<MovieResults>
  fun loadWatchlistMovies(): Observable<MovieResults>
  fun loadPopularMovies(): Observable<MovieResults>
  fun loadUpcomingMovies(): Observable<MovieResults>
}

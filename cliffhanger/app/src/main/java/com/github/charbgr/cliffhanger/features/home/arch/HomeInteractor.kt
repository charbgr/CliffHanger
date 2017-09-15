package com.github.charbgr.cliffhanger.features.home.arch

import com.github.charbgr.cliffhanger.features.home.arch.state.PartialChange
import com.github.charbgr.cliffhanger.network.tmdb.dao.MovieResults
import io.reactivex.Observable

interface HomeInteractor {
  fun loadTopRatedMovies(): Observable<PartialChange>
  fun loadNowPlayingMovies(): Observable<PartialChange>
  fun loadWatchlistMovies(): Observable<MovieResults>
  fun loadPopularMovies(): Observable<PartialChange>
  fun loadUpcomingMovies(): Observable<PartialChange>
}

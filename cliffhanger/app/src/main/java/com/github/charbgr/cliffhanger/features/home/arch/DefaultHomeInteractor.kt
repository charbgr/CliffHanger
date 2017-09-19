package com.github.charbgr.cliffhanger.features.home.arch

import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.features.home.arch.state.PartialChange
import com.github.charbgr.cliffhanger.network.tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.network.tmdb.dao.MovieResults
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class DefaultHomeInteractor : HomeInteractor {

  private val tmdbAPI: TmdbAPI = TmdbAPI.create(Schedulers.io())

  override fun loadTopRatedMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.topRatedMovies(1)
        .map { PartialChange.Loaded(MovieCategory.TopRated, it) as PartialChange }
        .startWith(PartialChange.Loading(MovieCategory.TopRated))
        .onErrorReturn { PartialChange.Failed(MovieCategory.TopRated, it) }
        .share()
  }

  override fun loadNowPlayingMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.nowPlayingMovie(1)
        .map { PartialChange.Loaded(MovieCategory.NowPlaying, it) as PartialChange }
        .startWith(PartialChange.Loading(MovieCategory.NowPlaying))
        .onErrorReturn { PartialChange.Failed(MovieCategory.NowPlaying, it) }
        .share()
  }

  override fun loadWatchlistMovies(): Observable<MovieResults> {
    return Observable.empty()
  }

  override fun loadPopularMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.popularMovies(1)
        .map { PartialChange.Loaded(MovieCategory.Popular, it) as PartialChange }
        .startWith(PartialChange.Loading(MovieCategory.Popular))
        .onErrorReturn { PartialChange.Failed(MovieCategory.Popular, it) }
        .share()
  }

  override fun loadUpcomingMovies(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.upcomingMovies(1)
        .map { PartialChange.Loaded(MovieCategory.Upcoming, it) as PartialChange }
        .startWith(PartialChange.Loading(MovieCategory.Upcoming))
        .onErrorReturn { PartialChange.Failed(MovieCategory.Upcoming, it) }
        .share()
  }

}

package com.github.charbgr.cliffhanger.features.browser.arch.interactor

import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange
import com.github.charbgr.cliffhanger.network.tmdb.TmdbAPI
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class TopRatedInteractor : MovieBrowserInteractor {
  private val tmdbAPI: TmdbAPI = TmdbAPI.create(Schedulers.io())

  override fun fetch(): Observable<PartialChange> {
    return tmdbAPI.movieDAO.topRatedMovies()
        .map { PartialChange.Loaded(it) as PartialChange }
        .startWith(PartialChange.Loading())
        .onErrorReturn { PartialChange.Failed(it) }
        .share()
  }

  override fun fetchMoreFrom(): Observable<PartialChange> {
    return Observable.empty()
  }

}

package com.github.charbgr.cliffhanger.feature.browser.arch.interactor

import com.github.charbgr.cliffhanger.api_tmdb.entity.MiniMovieEntityMapper.transform
import com.github.charbgr.cliffhanger.di.Deppie
import com.github.charbgr.cliffhanger.feature.browser.arch.state.PartialChange
import com.github.charbgr.cliffhanger.shared.repository.MovieRepository
import io.reactivex.Observable

class UpcomingInteractor : MovieBrowserInteractor {
  private val repository: MovieRepository = Deppie.getInstance().movieRepository

  override fun fetch(page: Int): Observable<PartialChange> {
    return repository.fetchUpcomingMovies(page)
      .toObservable()
      .map { PartialChange.Loaded(transform(it.results), it.page) as PartialChange }
      .startWith(PartialChange.Loading(page != 1))
      .onErrorReturn { PartialChange.Failed(it) }
      .share()
  }
}

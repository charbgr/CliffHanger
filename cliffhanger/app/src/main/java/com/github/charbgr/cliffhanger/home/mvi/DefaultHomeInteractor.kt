package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.Movie
import io.reactivex.Observable

class DefaultHomeInteractor : HomeInteractor {

  override fun loadData(): Observable<Movie> {
    return Observable
        .just(Movie(1, "Fight club"))
  }

}

package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.Movie
import io.reactivex.Observable

interface HomeInteractor {
  fun loadData(): Observable<Movie>
}

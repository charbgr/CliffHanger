package com.github.charbgr.cliffhanger.features.detail.arch

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

internal class MovieDetailRobot(internal val presenter: Presenter) {

  private val movieIntentPubSub = PublishSubject.create<Int>()
  internal val view: View = object : View {
    override fun fetchMovieIntent(): Observable<Int> = movieIntentPubSub.hide()
  }

  init {
    presenter.init(view)
    presenter.bindIntents()
  }

  fun fireMovieIntent(id: Int) {
    movieIntentPubSub.onNext(id)
  }

  fun fireMovieIntent(throwable: Throwable) {
    movieIntentPubSub.onError(throwable)
  }
}

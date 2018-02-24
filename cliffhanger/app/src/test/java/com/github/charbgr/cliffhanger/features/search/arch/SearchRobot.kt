package com.github.charbgr.cliffhanger.features.search.arch

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

internal class SearchRobot(internal val presenter: Presenter) {

  private val searchPubSub = PublishSubject.create<CharSequence>()
  internal val view: View = object : View {
    override fun searchIntent(): Observable<CharSequence> = searchPubSub.hide()
  }

  init {
    presenter.init(view)
    presenter.bindIntents()
  }

  fun fireSearchIntent(query: CharSequence) {
    searchPubSub.onNext(query)
  }
}

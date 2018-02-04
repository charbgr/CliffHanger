package com.github.charbgr.cliffhanger.shared.arch

import io.reactivex.Observable

abstract class MviPresenter<V : View, RenderType : Any> : RxPresenter<V>() {
  abstract fun bindIntents()
  abstract fun renders(): Observable<RenderType>

  protected fun <I> intent(observable: Observable<I>?): Observable<I> {
    return observable ?: Observable.empty<I>()
  }
}

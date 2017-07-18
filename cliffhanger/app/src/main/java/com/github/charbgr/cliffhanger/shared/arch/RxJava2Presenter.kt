package com.github.charbgr.cliffhanger.shared.arch

import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

open class RxJava2Presenter<T : View> : BasePresenter<T>() {
  private var disposable: CompositeDisposable = CompositeDisposable()

  override fun init(view: T) {
    super.init(view)
    disposable = CompositeDisposable()
  }

  override fun destroy() {
    super.destroy()
    disposable.dispose()
  }

  protected fun <I> intent(observable: Observable<I>?) : Observable<I> {
    return observable ?: Observable.empty<I>()
  }
}
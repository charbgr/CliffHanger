package com.github.charbgr.cliffhanger.shared.arch

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

open class RxPresenter<T : View> : BasePresenter<T>() {
  protected var disposable: CompositeDisposable = CompositeDisposable()

  override fun destroy() {
    super.destroy()
    disposable.clear()
  }

  protected fun register(vararg useCases: UseCase<*, *>) {
    useCases.forEach {
      it.disposable.addTo(disposable)
    }
  }

}

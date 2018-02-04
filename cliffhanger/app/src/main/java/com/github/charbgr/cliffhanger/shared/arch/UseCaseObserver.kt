package com.github.charbgr.cliffhanger.shared.arch

import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.subscribers.DisposableSubscriber
import timber.log.Timber

object UseCaseObserver {

  open class RxSingle<T> : DisposableSingleObserver<T>() {
    override fun onSuccess(value: T) {}
    override fun onError(e: Throwable) {
      Timber.e(e)
    }
  }

  open class RxObservable<T> : DisposableObserver<T>() {
    override fun onComplete() {}
    override fun onNext(value: T) {}
    override fun onError(e: Throwable) {
      Timber.e(e)
    }
  }

  open class RxFlowable<T> : DisposableSubscriber<T>() {
    override fun onComplete() {}
    override fun onNext(value: T) {}
    override fun onError(e: Throwable) {
      Timber.e(e)
    }
  }

}

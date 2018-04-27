package com.github.charbgr.cliffhanger.shared.arch

import com.github.charbgr.arch.UseCaseObserver
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

abstract class UseCase<out Type, in Params> where Type : Any {

  val disposable = CompositeDisposable()

  fun dispose() = disposable.dispose()

  abstract fun build(params: Params): Type

  abstract class RxSingle<T, in P> : UseCase<Single<T>, P>() {
    fun execute(observer: UseCaseObserver.RxSingle<T>, params: P) =
        build(params).subscribeWith(observer).addTo(disposable)
  }

  abstract class RxObservable<T, in P> : UseCase<Observable<T>, P>() {
    fun execute(observer: UseCaseObserver.RxObservable<T>, params: P) =
        build(params).subscribeWith(observer).addTo(disposable)
  }

  abstract class RxFlowable<T, in P> : UseCase<Flowable<T>, P>() {
    fun execute(subscriber: UseCaseObserver.RxFlowable<T>, params: P) =
        build(params).subscribeWith(subscriber).addTo(disposable)
  }

  abstract class RxCompletable<in P> : UseCase<Completable, P>() {
    fun execute(onComplete: () -> Unit = {}, params: P) =
        build(params).subscribe(onComplete).addTo(disposable)
  }
}

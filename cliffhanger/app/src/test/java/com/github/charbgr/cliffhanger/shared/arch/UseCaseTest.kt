package com.github.charbgr.cliffhanger.shared.arch

import com.github.charbgr.arch.UseCaseObserver
import com.github.charbgr.cliffhanger.UnitTest
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class UseCaseTest : UnitTest() {

  @Test
  fun test_dispose_on_single_use_case() {
    val useCase = TestRxSingleUseCase()
    useCase.execute(TestUseCaseRxSingleObserver(), Unit)
    assertDisposing(useCase)
  }

  @Test
  fun test_dispose_on_observable_use_case() {
    val useCase = TestRxObservableUseCase()
    useCase.execute(TestUseCaseRxObservableObserver(), Unit)
    assertDisposing(useCase)
  }

  @Test
  fun test_dispose_on_flowable_use_case() {
    val useCase = TestRxFlowableUseCase()
    useCase.execute(TestUseCaseRxFlowableObserver(), Unit)
    assertDisposing(useCase)
  }


  @Test
  fun test_dispose_on_completable_use_case() {
    val useCase = TestUseCaseRxCompletable()
    useCase.execute({}, Unit)
    assertDisposing(useCase)
  }

  private fun assertDisposing(useCase: UseCase<Any, Unit>) {
    assertFalse(useCase.disposable.isDisposed)
    useCase.dispose()
    assertTrue(useCase.disposable.isDisposed)
  }


  private class TestUseCaseRxSingleObserver : UseCaseObserver.RxSingle<String>()
  private class TestRxSingleUseCase : UseCase.RxSingle<String, Unit>() {
    override fun build(params: Unit): Single<String> = Single.just("test")
  }

  private class TestUseCaseRxObservableObserver : UseCaseObserver.RxObservable<String>()
  private class TestRxObservableUseCase : UseCase.RxObservable<String, Unit>() {
    override fun build(params: Unit): Observable<String> = Observable.just("test")
  }

  private class TestUseCaseRxFlowableObserver : UseCaseObserver.RxFlowable<String>()
  private class TestRxFlowableUseCase : UseCase.RxFlowable<String, Unit>() {
    override fun build(params: Unit): Flowable<String> = Flowable.just("test")
  }

  private class TestUseCaseRxCompletable : UseCase.RxCompletable<Unit>() {
    override fun build(params: Unit): Completable = Completable.complete()
  }
}

package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.shared.arch.MviPresenter
import com.github.charbgr.cliffhanger.shared.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.shared.arch.UseCaseObserver.RxObservable
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject

internal class Presenter(
    private val schedulers: SchedulerProvider = SchedulerProvider.Default,
    private val movieUseCase: GetMovieUseCase = GetMovieUseCase()
) : MviPresenter<View, Pair<PartialChange, ViewModel>>() {

  init {
    register(movieUseCase)
  }

  private val stateReducer = StateReducer()
  private val renders = BehaviorSubject.createDefault(stateReducer.initial)

  override fun bindIntents() {
    val getMovieIntent = intent(viewWRef.get()?.fetchMovieIntent()).share()

    getMovieIntent
        .switchMap { movieUseCase.build(it) }
        .observeOn(schedulers.io())
        .scan(renders.value, stateReducer.reduce)
        .subscribeWith(object : RxObservable<Pair<PartialChange, ViewModel>>() {
          override fun onNext(value: Pair<PartialChange, ViewModel>) {
            dispatchViewRender(value)
          }
        })
        .addTo(disposable)
  }

  override fun renders(): Observable<Pair<PartialChange, ViewModel>> =
      renders.hide().share().observeOn(schedulers.ui())

  private fun dispatchViewRender(render: Pair<PartialChange, ViewModel>) {
    renders.onNext(render)
    // TODO ADD TO CACHE
  }

}

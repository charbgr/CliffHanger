package com.github.charbgr.cliffhanger.features.search.arch

import com.github.charbgr.arch.MviPresenter
import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.arch.UseCaseObserver.RxObservable
import com.github.charbgr.cliffhanger.features.search.arch.SearchMovieUseCase.Params
import com.github.charbgr.cliffhanger.shared.extensions.AndroidSchedulerProvider
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject

internal class Presenter(
    private val schedulers: SchedulerProvider = AndroidSchedulerProvider,
    private val searchUseCase: SearchMovieUseCase = SearchMovieUseCase()
) : MviPresenter<View, Pair<PartialChange, ViewModel>>() {

  init {
    register(searchUseCase)
  }

  private val stateReducer = StateReducer()
  private val renders = BehaviorSubject.createDefault(stateReducer.initial)

  override fun bindIntents() {
    val searchIntent = intent(viewWRef.get()?.searchIntent()).share()

    searchIntent
        .switchMap { searchUseCase.build(Params(it, 1)) }
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
  }

}

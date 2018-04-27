package com.github.charbgr.feature.browser.arch

import com.github.charbgr.arch.MviPresenter
import com.github.charbgr.arch.UseCaseObserver
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.feature.browser.arch.interactor.MovieBrowserInteractor
import com.github.charbgr.feature.browser.arch.interactor.MovieBrowserInteractorFactory
import com.github.charbgr.feature.browser.arch.state.PartialChange
import com.github.charbgr.feature.browser.arch.state.StateReducer
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject

class BrowserPresenter(
    private val movieCategory: MovieCategory,
    private val scheduler: Scheduler = AndroidSchedulers.mainThread()
) : MviPresenter<BrowserView, BrowserViewModel>() {

  private val interactor: MovieBrowserInteractor by lazy {
    MovieBrowserInteractorFactory.createInteractor(movieCategory)
  }

  private val stateReducer: StateReducer = StateReducer()
  private val viewModelRenders: BehaviorSubject<BrowserViewModel> = BehaviorSubject.create()

  override fun bindIntents() {
    val loadDataIntent = intent(viewWRef.get()?.loadDataIntent())
        .switchMap { interactor.fetch(page = 1) }

    val loadMoreIntent = intent(viewWRef.get()?.infiniteScrollIntent())
        .filter { viewModel().isLoading }
        .map { viewModel().page + 1 }
        .distinctUntilChanged()
        .switchMap {
          interactor.fetch(it)
        }

    val allIntentsObservable = Observable.merge(loadDataIntent, loadMoreIntent)
        .observeOn(scheduler)

    allIntentsObservable
        .scan(stateReducer.initState(movieCategory), stateReducer.reduce)
        .subscribeWith(object : UseCaseObserver.RxObservable<Pair<PartialChange, BrowserViewModel>>() {
          override fun onNext(value: Pair<PartialChange, BrowserViewModel>) {
            dispatchViewRender(value.second, value.first)
          }
        })
  }

  override fun renders(): Observable<BrowserViewModel> = viewModelRenders.hide().share()

  fun viewModel(): BrowserViewModel = viewModelRenders.value

  private fun dispatchViewRender(viewModel: BrowserViewModel, partialChange: PartialChange) {
    viewModelRenders.onNext(viewModel)
    viewWRef.get()?.render(viewModel, partialChange)
  }

}

package com.github.charbgr.cliffhanger.features.browser.arch

import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.features.browser.arch.interactor.MovieBrowserInteractor
import com.github.charbgr.cliffhanger.features.browser.arch.interactor.MovieBrowserInteractorFactory
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Init
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Loading
import com.github.charbgr.cliffhanger.features.browser.arch.state.StateReducer
import com.github.charbgr.cliffhanger.shared.arch.RxJava2Presenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class BrowserPresenter(
    private val movieCategory: MovieCategory,
    private val scheduler: Scheduler = AndroidSchedulers.mainThread()) : RxJava2Presenter<BrowserView>() {

  private val interactor: MovieBrowserInteractor by lazy {
    MovieBrowserInteractorFactory.createInteractor(movieCategory)
  }

  private val stateReducer: StateReducer = StateReducer()
  var viewModel: BrowserViewModel = BrowserViewModel.initial(movieCategory)
    private set

  fun bindIntents() {
    val loadDataIntent = intent(viewWRef.get()?.loadDataIntent())
        .switchMap { interactor.fetch(page = 1) }

    val loadMoreIntent = intent(viewWRef.get()?.infiniteScrollIntent())
        .filter { viewModel.lastPartialChange !is Loading || viewModel.lastPartialChange !is Init }
        .map { (viewModel.movieResults?.page ?: 0) + 1 }
        .distinctUntilChanged()
        .switchMap {
          interactor.fetch(it)
        }

    val allIntentsObservable = Observable.merge(loadDataIntent, loadMoreIntent)
        .observeOn(scheduler)

    allIntentsObservable
        .scan(viewModel, stateReducer.reduce)
        .subscribeWith(object : DisposableObserver<BrowserViewModel>() {
          override fun onComplete() {
          }

          override fun onNext(viewModel: BrowserViewModel) {
            dispatchViewRender(viewModel)
          }

          override fun onError(e: Throwable?) {
            Timber.wtf(e)
          }

        })
  }

  private fun dispatchViewRender(viewModel: BrowserViewModel) {
    this.viewModel = viewModel
    viewWRef.get()?.render(viewModel)
  }

}

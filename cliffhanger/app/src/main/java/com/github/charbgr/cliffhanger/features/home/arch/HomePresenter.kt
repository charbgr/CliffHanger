package com.github.charbgr.cliffhanger.features.home.arch

import com.github.charbgr.arch.MviPresenter
import com.github.charbgr.cliffhanger.features.home.arch.state.HomeStateReducer
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import timber.log.Timber

class HomePresenter(
    private val interactor: HomeInteractor = DefaultHomeInteractor(),
    private val scheduler: Scheduler = AndroidSchedulers.mainThread())
  : MviPresenter<HomeView, HomeViewModel>() {

  private val stateReducer = HomeStateReducer()
  private var viewModel: HomeViewModel = HomeViewModel.initial()

  override fun bindIntents() {

    val topRatedClickIntent = intent(Observable.just(true))
        .switchMap { interactor.loadTopRatedMovies() }

    val nowPlayingIntent = intent(Observable.just(true))
        .switchMap { interactor.loadNowPlayingMovies() }

    val upcomingIntent = intent(Observable.just(true))
        .switchMap { interactor.loadUpcomingMovies() }

    val popularIntent = intent(Observable.just(true))
        .switchMap { interactor.loadPopularMovies() }

    val allIntents = Observable.merge(
        listOf(topRatedClickIntent, nowPlayingIntent, upcomingIntent, popularIntent)
    ).observeOn(scheduler)

    allIntents
        .scan(viewModel, stateReducer.reduce)
        .subscribeWith(object : DisposableObserver<HomeViewModel>() {
          override fun onNext(viewModel: HomeViewModel) {
            this@HomePresenter.viewModel = viewModel
            dispatchViewRender(viewModel)
          }

          override fun onError(e: Throwable?) {
            Timber.wtf(e)
          }

          override fun onComplete() {
          }

        })
  }

  override fun renders(): Observable<HomeViewModel> = Observable.just(viewModel)

  private fun dispatchViewRender(viewModel: HomeViewModel) {
    viewWRef.get()?.render(viewModel)
  }

}

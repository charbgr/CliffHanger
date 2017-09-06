package com.github.charbgr.cliffhanger.features.home.arch

import com.github.charbgr.cliffhanger.shared.arch.RxJava2Presenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver

class HomePresenter(
    private val interactor: HomeInteractor = DefaultHomeInteractor(),
    private val scheduler: Scheduler = AndroidSchedulers.mainThread())
  : RxJava2Presenter<HomeView>() {

  fun bindIntents() {

    val showLoaderViewModel = HomeViewModel(true, null)

    val topRatedClickIntent = intent(viewWRef.get()?.topRatedClickIntent())
        .switchMap { interactor.loadTopRatedMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val nowPlayingClickIntent = intent(viewWRef.get()?.nowPlayingClickIntent())
        .switchMap { interactor.loadNowPlayingMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val watchlistClickIntent = intent(viewWRef.get()?.watchlistClickIntent())
        .switchMap { interactor.loadWatchlistMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val popularClickIntent = intent(viewWRef.get()?.popularClickIntent())
        .switchMap { interactor.loadPopularMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val upcomingClickIntent = intent(viewWRef.get()?.upcomingClickIntent())
        .switchMap { interactor.loadUpcomingMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val allIntents = Observable.merge(
        listOf(topRatedClickIntent, nowPlayingClickIntent, watchlistClickIntent, popularClickIntent,
            upcomingClickIntent)
    ).observeOn(scheduler)

    allIntents.subscribe(object : DisposableObserver<HomeViewModel>() {
      override fun onError(e: Throwable?) {
      }

      override fun onComplete() {
      }

      override fun onNext(viewModel: HomeViewModel?) {
        viewModel?.let {
          viewWRef.get()?.render(it)
        }
      }

    })
  }

}

package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.shared.arch.RxJava2Presenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class HomePresenter(
    private val interactor: HomeInteractor = DefaultHomeInteractor(),
    private val scheduler: Scheduler = AndroidSchedulers.mainThread())
  : RxJava2Presenter<HomeView>() {

  fun bindIntents() {

    val showLoaderViewModel = HomeViewModel(true, null)

    val topRatedClickIntent = intent(viewWRef?.get()?.topRatedClickIntent())
        .switchMap { interactor.loadTopRatedMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val nowPlayingClickIntent = intent(viewWRef?.get()?.nowPlayingClickIntent())
        .switchMap { interactor.loadNowPlayingMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val watchlistClickIntent = intent(viewWRef?.get()?.watchlistClickIntent())
        .switchMap { interactor.loadWatchlistMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val popularClickIntent = intent(viewWRef?.get()?.popularClickIntent())
        .switchMap { interactor.loadPopularMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val upcomingClickIntent = intent(viewWRef?.get()?.upcomingClickIntent())
        .switchMap { interactor.loadUpcomingMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val allIntents = Observable.merge(
        listOf(topRatedClickIntent, nowPlayingClickIntent, watchlistClickIntent, popularClickIntent,
            upcomingClickIntent)
    ).observeOn(scheduler)
  }

}

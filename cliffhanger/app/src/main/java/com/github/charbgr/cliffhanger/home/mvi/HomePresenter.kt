package com.github.charbgr.cliffhanger.home.mvi

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class HomePresenter(
    private val interactor: HomeInteractor = DefaultHomeInteractor(),
    private val scheduler: Scheduler = AndroidSchedulers.mainThread())
  : MviBasePresenter<HomeView, HomeViewModel>() {

  override fun bindIntents() {

    val showLoaderViewModel = HomeViewModel(true, null)

    val topRatedClickIntent = intent { it.topRatedClickIntent() }
        .switchMap { interactor.loadTopRatedMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val nowPlayingClickIntent = intent { it.nowPlayingClickIntent() }
        .switchMap { interactor.loadNowPlayingMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val watchlistClickIntent = intent { it.watchlistClickIntent() }
        .switchMap { interactor.loadWatchlistMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val popularClickIntent = intent { it.popularClickIntent() }
        .switchMap { interactor.loadPopularMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val upcomingClickIntent = intent { it.upcomingClickIntent() }
        .switchMap { interactor.loadUpcomingMovies().map { HomeViewModel(false, it) } }
        .startWith(showLoaderViewModel)

    val allIntents = Observable.merge(
        listOf(topRatedClickIntent, nowPlayingClickIntent, watchlistClickIntent, popularClickIntent,
            upcomingClickIntent)
    ).observeOn(scheduler)

    subscribeViewState(allIntents.distinctUntilChanged(), HomeView::render)
  }

}

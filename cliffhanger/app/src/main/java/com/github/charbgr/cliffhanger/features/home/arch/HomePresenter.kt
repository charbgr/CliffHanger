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

    val topRatedClickIntent = intent(Observable.just(true))
        .switchMap { interactor.loadTopRatedMovies().map { HomeViewModel(false, topRated = it) } }
        .startWith(showLoaderViewModel)


    val nowPlayingIntent = intent(Observable.just(true))
        .switchMap { interactor.loadNowPlayingMovies().map { HomeViewModel(false, nowPlaying = it) } }
        .startWith(showLoaderViewModel)

    val upcomingIntent = intent(Observable.just(true))
        .switchMap { interactor.loadUpcomingMovies().map { HomeViewModel(false, upcoming = it) } }
        .startWith(showLoaderViewModel)

    val popularIntent = intent(Observable.just(true))
        .switchMap { interactor.loadPopularMovies().map { HomeViewModel(false, popular = it) } }
        .startWith(showLoaderViewModel)


    val allIntents = Observable.merge(
        listOf(topRatedClickIntent, nowPlayingIntent, upcomingIntent, popularIntent)
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

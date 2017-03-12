package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.home.NavigationItem
import com.github.charbgr.cliffhanger.home.NavigationItem.NOW_PLAYING
import com.github.charbgr.cliffhanger.home.NavigationItem.POPULAR
import com.github.charbgr.cliffhanger.home.NavigationItem.TOP_RATED
import com.github.charbgr.cliffhanger.home.NavigationItem.UPCOMING
import com.github.charbgr.cliffhanger.home.NavigationItem.WATCHLIST
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject



class HomeViewRobot(val presenter: HomePresenter) {

  private val navigationIntent: PublishSubject<NavigationItem> = PublishSubject.create()
  private val renderEventSubject: ReplaySubject<HomeViewModel> = ReplaySubject.create()
  private val renderEvents: MutableList<HomeViewModel> = mutableListOf()

  private val homeView: HomeView = object : HomeView {
    override fun bottomNavigationIntent(): Observable<NavigationItem> = navigationIntent

    override fun render(viewModel: HomeViewModel) {
      renderEvents.add(viewModel)
      renderEventSubject.onNext(viewModel)
    }
  }

  init {
    presenter.attachView(homeView)
  }

  fun fireTopRatedIntent() {
    fireNavigationIntent(TOP_RATED)
  }

  fun fireNowPlayingIntent() {
    fireNavigationIntent(NOW_PLAYING)
  }

  fun fireWatchListIntent() {
    fireNavigationIntent(WATCHLIST)
  }

  fun firePopularIntent() {
    fireNavigationIntent(POPULAR)
  }

  fun fireUpcomingIntent() {
    fireNavigationIntent(UPCOMING)
  }

  fun fireNavigationIntent(navigationItem: NavigationItem) {
    navigationIntent.onNext(navigationItem)
  }

}

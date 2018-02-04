package com.github.charbgr.cliffhanger.features.home.arch

import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject


class HomeViewRobot(private val presenter: HomePresenter) {

  private val topRatedClickIntent: PublishSubject<Boolean> = PublishSubject.create()
  private val nowPlayingClickIntent: PublishSubject<Boolean> = PublishSubject.create()
  private val watchlistClickIntent: PublishSubject<Boolean> = PublishSubject.create()
  private val popularClickIntent: PublishSubject<Boolean> = PublishSubject.create()
  private val upcomingClickIntent: PublishSubject<Boolean> = PublishSubject.create()
  private val renderEventSubject: ReplaySubject<HomeViewModel> = ReplaySubject.create()
  private val renderEvents: MutableList<HomeViewModel> = mutableListOf()

//  private val homeView: HomeView = object : HomeView {
//    override fun topRatedClickIntent(): Observable<Boolean> = topRatedClickIntent
//    override fun nowPlayingClickIntent(): Observable<Boolean> = nowPlayingClickIntent
//    override fun watchlistClickIntent(): Observable<Boolean> = watchlistClickIntent
//    override fun popularClickIntent(): Observable<Boolean> = popularClickIntent
//    override fun upcomingClickIntent(): Observable<Boolean> = upcomingClickIntent
//
//    override fun render(viewModel: HomeViewModel) {
//      renderEvents.add(viewModel)
//      renderEventSubject.onNext(viewModel)
//    }
//  }
//
//  init {
//    presenter.attachView(homeView)
//  }
//
//  fun fireTopRatedIntent() {
//    topRatedClickIntent.onNext(true)
//  }
//
//  fun fireNowPlayingIntent() {
//    nowPlayingClickIntent.onNext(true)
//  }
//
//  fun fireWatchListIntent() {
//    watchlistClickIntent.onNext(true)
//  }
//
//  fun firePopularIntent() {
//    popularClickIntent.onNext(true)
//  }
//
//  fun fireUpcomingIntent() {
//    upcomingClickIntent.onNext(true)
//  }
//
//  fun assertViewStateRendered(vararg viewModels: HomeViewModel) {
//    val eventsCount = viewModels.size
////    renderEventSubject.take(eventsCount).blockingSubscribe()
////
////    /*
////    // Wait for few milli seconds to ensure that no more render events have occurred
////    // before finishing the test and checking expectations (asserts)
////    try {
////      Thread.sleep(5000);
////    } catch (InterruptedException e) {
////      e.printStackTrace();
////    }
////    */
////
////    if (renderEventSubject.values.length > eventsCount) {
////      Assert.fail("Expected to wait for "
////          + eventsCount
////          + ", but there were "
////          + renderEventSubject.values.length
////          + " Events in total, which is more than expected: "
////          + arrayToString(renderEventSubject.values))
////    }
//
//    Assert.assertEquals(viewModels.asList(), renderEvents)
//  }

}

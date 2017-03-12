package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.test_factories.MiniMovieDtoFactory
import com.github.charbgr.cliffhanger.tmdb.dao.MovieResults
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.amshove.kluent.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

class HomePresenterTest {

  lateinit var interactor: HomeInteractor
  lateinit var presenter: HomePresenter
  lateinit var robot: HomeViewRobot

  @Before
  fun setup() {
    interactor = mock(HomeInteractor::class)
    presenter = HomePresenter(interactor, Schedulers.trampoline())
    robot = HomeViewRobot(presenter)
  }

  @Test
  fun test_loading_toprated_movies() {
    mockInteractor()
    robot.fireTopRatedIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched)
  }

  @Test
  fun test_loading_now_playing_movies() {
    mockInteractor()
    robot.fireTopRatedIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched)
  }

  @Test
  fun test_loading_watchlist_movies() {
    mockInteractor()
    robot.fireTopRatedIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched)
  }

  @Test
  fun test_loading_popular_movies() {
    mockInteractor()
    robot.fireTopRatedIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched)
  }

  @Test
  fun test_loading_upcoming_movies() {
    mockInteractor()
    robot.fireTopRatedIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched)
  }

  private fun dummyMovieResults(): MovieResults {
    return MovieResults(1, listOf(MiniMovieDtoFactory.EMPTY), 2, 2)
  }

  private fun mockInteractor() {
    `when`(interactor.loadTopRatedMovies()).thenReturn(Observable.just(dummyMovieResults()))
    `when`(interactor.loadNowPlayingMovies()).thenReturn(Observable.just(dummyMovieResults()))
    `when`(interactor.loadWatchlistMovies()).thenReturn(Observable.just(dummyMovieResults()))
    `when`(interactor.loadPopularMovies()).thenReturn(Observable.just(dummyMovieResults()))
    `when`(interactor.loadUpcomingMovies()).thenReturn(Observable.just(dummyMovieResults()))
  }
}
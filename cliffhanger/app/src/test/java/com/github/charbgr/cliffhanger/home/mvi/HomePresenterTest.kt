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
    robot.fireNowPlayingIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched)
  }

  @Test
  fun test_loading_watchlist_movies() {
    mockInteractor()
    robot.fireWatchListIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched)
  }

  @Test
  fun test_loading_popular_movies() {
    mockInteractor()
    robot.firePopularIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched)
  }

  @Test
  fun test_loading_upcoming_movies() {
    mockInteractor()
    robot.fireUpcomingIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched)
  }

  @Test
  fun test_showing_loader_after_fetched_data() {
    mockInteractor()
    robot.fireTopRatedIntent()

    mockInteractor(dummyMovieResults2())
    robot.fireUpcomingIntent()

    val showLoader = HomeViewModel(true, null)
    val fetched = HomeViewModel(false, dummyMovieResults())

    robot.assertViewStateRendered(showLoader, fetched, showLoader, fetched)
  }

  private fun dummyMovieResults(): MovieResults {
    return MovieResults(1, listOf(MiniMovieDtoFactory.EMPTY), 1, 1)
  }

  private fun dummyMovieResults2(): MovieResults {
    return MovieResults(1, listOf(MiniMovieDtoFactory.EMPTY, MiniMovieDtoFactory.EMPTY), 2, 1)
  }

  private fun mockInteractor(movieResults: MovieResults = dummyMovieResults()) {
    `when`(interactor.loadTopRatedMovies()).thenReturn(Observable.just(movieResults))
    `when`(interactor.loadNowPlayingMovies()).thenReturn(Observable.just(movieResults))
    `when`(interactor.loadWatchlistMovies()).thenReturn(Observable.just(movieResults))
    `when`(interactor.loadPopularMovies()).thenReturn(Observable.just(movieResults))
    `when`(interactor.loadUpcomingMovies()).thenReturn(Observable.just(movieResults))
  }
}
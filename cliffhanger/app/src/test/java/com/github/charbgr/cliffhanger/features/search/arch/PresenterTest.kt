package com.github.charbgr.cliffhanger.features.search.arch

import com.github.charbgr.cliffhanger.UnitTest
import com.github.charbgr.cliffhanger.domain.MiniMovie
import com.github.charbgr.cliffhanger.domain.SearchResults
import com.github.charbgr.cliffhanger.network.tmdb.entity.MiniMovieEntity
import com.github.charbgr.cliffhanger.network.tmdb.entity.SearchResultsEntity
import com.github.charbgr.cliffhanger.test_factories.MiniMovieFactory
import com.github.charbgr.cliffhanger.test_factories.MockMovieRepository
import org.junit.Test

class PresenterTest : UnitTest() {

  @Test
  fun test_binding_intents() {
    val movieEntity = MiniMovieFactory.FightClubEntity
    val movie = MiniMovieFactory.FightClub

    val presenter = presenter(create(movie = movieEntity))
    val robot = SearchRobot(presenter)
    val testObserver = presenter.renders().test()

    testObserver.assertValues(
        Pair(PartialChange.Initial, ViewModel.Initial())
    )
  }

  @Test
  fun test_searching_intent() {
    val movieEntity = MiniMovieFactory.FightClubEntity
    val movie = MiniMovieFactory.FightClub

    val presenter = presenter(create(movie = movieEntity))
    val robot = SearchRobot(presenter)
    val testObserver = presenter.renders().test()
    robot.fireSearchIntent("foo")

    testObserver.assertValues(
        Pair(PartialChange.Initial, ViewModel.Initial()),
        Pair(PartialChange.InProgress(false), ViewModel(true, false, false, null, null)),
        Pair(PartialChange.Success(create(movie = movie), false),
            ViewModel(false, true, false, null, listOf(movie)))
    )
  }

  private fun create(page: Int = 1, movie: MiniMovie): SearchResults =
      SearchResults(page, listOf(movie))

  private fun create(page: Int = 1, movie: MiniMovieEntity): SearchResultsEntity =
      SearchResultsEntity(page, listOf(movie))


  private fun presenter(searchResultsEntity: SearchResultsEntity): Presenter {
    return Presenter(fakeSchedulerProvider,
        SearchMovieUseCase(MockMovieRepository(searchResultsEntity = searchResultsEntity)))
  }

}

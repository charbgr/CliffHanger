package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.UnitTest
import com.github.charbgr.cliffhanger.api_tmdb.entity.FullMovieEntity
import com.github.charbgr.cliffhanger.test_factories.MockMovieRepository
import com.github.charbgr.cliffhanger.test_factories.MovieFactory
import org.junit.Test

internal class PresenterTest : UnitTest() {

  @Test
  fun test_binding_intents() {
    val movieEntity = MovieFactory.FightClubEntity
    val movie = MovieFactory.FightClub

    val presenter = create(movieEntity)
    val robot = MovieDetailRobot(presenter)
    val testObserver = presenter.renders().test()

    testObserver.assertValues(
        Pair(PartialChange.Initial, ViewModel.Initial())
    )
  }

  @Test
  fun test_fetching_movie() {
    val movieEntity = MovieFactory.FightClubEntity
    val movie = MovieFactory.FightClub

    val presenter = create(movieEntity)
    val robot = MovieDetailRobot(presenter)
    val testObserver = presenter.renders().test()

    robot.fireMovieIntent(movieEntity.id!!)

    testObserver.assertValues(
        Pair(PartialChange.Initial, ViewModel.Initial()),
        Pair(PartialChange.InProgress, ViewModel(true, false, false, null, null)),
        Pair(PartialChange.Success(movie), ViewModel(false, true, false, null, movie))
    )
  }


  private fun create(movie: FullMovieEntity): Presenter {
    return Presenter(fakeSchedulerProvider, GetMovieUseCase(MockMovieRepository(movie)))
  }

}

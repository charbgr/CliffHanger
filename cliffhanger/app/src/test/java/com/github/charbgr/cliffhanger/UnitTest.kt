package com.github.charbgr.cliffhanger

import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.di.Deppie
import com.github.charbgr.cliffhanger.di.DeppieContext
import com.github.charbgr.cliffhanger.shared.repository.MovieRepository
import com.github.charbgr.cliffhanger.test_factories.MockMovieRepository
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

open class UnitTest {

  protected val fakeSchedulerProvider = object : SchedulerProvider {
    override fun ui(): Scheduler = Schedulers.trampoline()
    override fun io(): Scheduler = Schedulers.trampoline()
  }

  protected fun mockDeppie(
    mockSchedulerProvider: SchedulerProvider = fakeSchedulerProvider,
    mockMovieRepository: MockMovieRepository = MockMovieRepository()
  ) {
    val mockDeppieContext = object : DeppieContext {
      override val schedulerProvider: SchedulerProvider = mockSchedulerProvider
      override val movieRepository: MovieRepository = mockMovieRepository
    }

    Deppie.init(mockDeppieContext, true)
  }

}

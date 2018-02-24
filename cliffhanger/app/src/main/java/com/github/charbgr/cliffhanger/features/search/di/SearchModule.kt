package com.github.charbgr.cliffhanger.features.search.di

import com.github.charbgr.cliffhanger.features.search.SearchController
import com.github.charbgr.cliffhanger.features.search.arch.Presenter
import com.github.charbgr.cliffhanger.features.search.arch.SearchMovieUseCase
import com.github.charbgr.cliffhanger.features.search.arch.UiBinder
import com.github.charbgr.cliffhanger.features.search.arch.View
import com.github.charbgr.cliffhanger.shared.arch.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
object SearchModule {

  @Provides
  @JvmStatic
  internal fun providesSearchView(searchController: SearchController): View = searchController

  @Provides
  @JvmStatic
  internal fun providesSearchBinder(searchController: SearchController): UiBinder =
      UiBinder(searchController)

  @Provides
  @JvmStatic
  internal fun providesSearchMovieUseCase(): SearchMovieUseCase = SearchMovieUseCase()

  @Provides
  @JvmStatic
  internal fun providesSchedulers(): SchedulerProvider = SchedulerProvider.Default

  @Provides
  @JvmStatic
  internal fun providesSearchPresenter(
      schedulerProvider: SchedulerProvider,
      useCase: SearchMovieUseCase,
      searchView: View
  ): Presenter {
    val presenter = Presenter(schedulerProvider, useCase)
    presenter.init(searchView)
    return presenter
  }
}

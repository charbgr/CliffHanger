package com.github.charbgr.cliffhanger.feature.search.di

import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.shared.extensions.AndroidSchedulerProvider
import com.github.charbgr.cliffhanger.feature.search.SearchController
import com.github.charbgr.cliffhanger.feature.search.arch.Presenter
import com.github.charbgr.cliffhanger.feature.search.arch.SearchMovieUseCase
import com.github.charbgr.cliffhanger.feature.search.arch.UiBinder
import com.github.charbgr.cliffhanger.feature.search.arch.View
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
  internal fun providesSchedulers(): SchedulerProvider = AndroidSchedulerProvider

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

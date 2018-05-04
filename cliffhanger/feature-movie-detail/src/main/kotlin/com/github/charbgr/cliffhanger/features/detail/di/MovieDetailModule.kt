package com.github.charbgr.cliffhanger.features.detail.di

import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.features.detail.MovieDetailActivity
import com.github.charbgr.cliffhanger.features.detail.arch.GetMovieUseCase
import com.github.charbgr.cliffhanger.features.detail.arch.Presenter
import com.github.charbgr.cliffhanger.features.detail.arch.UiBinder
import com.github.charbgr.cliffhanger.features.detail.arch.View
import com.github.charbgr.cliffhanger.shared.extensions.AndroidSchedulerProvider
import dagger.Module
import dagger.Provides

@Module
internal object MovieDetailModule {

  @Provides
  @JvmStatic
  internal fun providesMovieDetailView(activity: MovieDetailActivity): View = activity

  @Provides
  @JvmStatic
  internal fun providesMovideDetailBinder(
    activity: MovieDetailActivity
  ): UiBinder = UiBinder(activity)

  @Provides
  @JvmStatic
  internal fun providesMovieUseCase(): GetMovieUseCase = GetMovieUseCase()

  @Provides
  @JvmStatic
  internal fun providesSchedulers(): SchedulerProvider = AndroidSchedulerProvider

  @Provides
  @JvmStatic
  internal fun providesMovideDetailPresenter(
    schedulerProvider: SchedulerProvider,
    movieUseCase: GetMovieUseCase,
    movieDetailView: View
  ): Presenter {
    val presenter = Presenter(schedulers = schedulerProvider, movieUseCase = movieUseCase)
    presenter.init(movieDetailView)
    return presenter
  }
}

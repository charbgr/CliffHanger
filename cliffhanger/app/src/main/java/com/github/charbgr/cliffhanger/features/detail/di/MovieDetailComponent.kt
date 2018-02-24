package com.github.charbgr.cliffhanger.features.detail.di

import com.github.charbgr.cliffhanger.features.detail.MovieDetailActivity
import com.github.charbgr.cliffhanger.features.detail.arch.View
import dagger.BindsInstance
import dagger.Component


@Component(modules = arrayOf(
    MovieDetailModule::class
))
internal interface MovieDetailComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun movieDetailView(movieDetailActivity: MovieDetailActivity): Builder

    fun build(): MovieDetailComponent
  }


  fun movieDetailView(): View
  fun inject(activity: MovieDetailActivity)

}

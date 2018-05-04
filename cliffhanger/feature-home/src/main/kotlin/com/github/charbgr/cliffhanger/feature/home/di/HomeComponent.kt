package com.github.charbgr.cliffhanger.features.home.di

import com.github.charbgr.cliffhanger.feature.home.HomeController
import com.github.charbgr.cliffhanger.feature.home.arch.HomeView
import dagger.BindsInstance
import dagger.Component

@Component(modules = arrayOf(
    HomeModule::class
))
interface HomeComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun homeController(homeController: HomeController): Builder

    fun build(): HomeComponent
  }

  fun homeView(): HomeView
  fun inject(homeController: HomeController)
}

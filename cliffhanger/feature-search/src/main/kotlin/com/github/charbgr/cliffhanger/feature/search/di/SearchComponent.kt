package com.github.charbgr.cliffhanger.feature.search.di

import com.github.charbgr.cliffhanger.feature.search.SearchController
import com.github.charbgr.cliffhanger.feature.search.arch.View
import dagger.BindsInstance
import dagger.Component

@Component(modules = arrayOf(
    SearchModule::class
))
interface SearchComponent {

  @Component.Builder
  interface Builder {
    @BindsInstance
    fun searchController(searchController: SearchController): Builder

    fun build(): SearchComponent
  }

  fun searchView(): View
  fun inject(searchController: SearchController)
}

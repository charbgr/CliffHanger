package com.github.charbgr.cliffhanger.di

import com.github.charbgr.arch.RealSchedulerProvider
import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.api_tmdb.di.TmdbModule

interface Deppie : SchedulerProvider, TmdbModule {

  companion object {

    private val deppieInstance: Deppie by lazy {
      RealDeppie(
        schedulerProvider = RealSchedulerProvider,
        tmdbModule = TmdbModule.Real(RealSchedulerProvider)
      )
    }

    fun getInstance(): Deppie = deppieInstance
  }
}

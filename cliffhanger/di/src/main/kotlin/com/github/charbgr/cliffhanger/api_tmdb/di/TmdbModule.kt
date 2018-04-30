package com.github.charbgr.cliffhanger.api_tmdb.di

import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.api_tmdb.TmdbAPI

interface TmdbModule {
  val tmdbApi: TmdbAPI

  class Real(private val schedulerProvider: SchedulerProvider) : TmdbModule {
    override val tmdbApi: TmdbAPI by lazy {
      TmdbAPI(schedulerProvider.io())
    }
  }
}

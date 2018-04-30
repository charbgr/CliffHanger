package com.github.charbgr.cliffhanger.di

import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.api_tmdb.di.TmdbModule

class RealDeppie(
  schedulerProvider: SchedulerProvider,
  tmdbModule: TmdbModule
) : Deppie,
  SchedulerProvider by schedulerProvider,
  TmdbModule by tmdbModule

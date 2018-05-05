package com.github.charbgr.cliffhanger.di

import com.github.charbgr.arch.RealSchedulerProvider
import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.shared.repository.MovieRepositoryModule
import com.github.charbgr.cliffhanger.shared.repository.RealMovieRepositoryModule

internal class RealDeppieContext(
  override val schedulerProvider: SchedulerProvider = RealSchedulerProvider,
  movieRepositoryModule: MovieRepositoryModule = RealMovieRepositoryModule(schedulerProvider)
) : DeppieContext,
  SchedulerProvider by schedulerProvider,
  MovieRepositoryModule by movieRepositoryModule

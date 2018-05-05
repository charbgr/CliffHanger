package com.github.charbgr.cliffhanger.di

import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.shared.repository.MovieRepositoryModule

interface DeppieContext : MovieRepositoryModule {
  val schedulerProvider: SchedulerProvider
}

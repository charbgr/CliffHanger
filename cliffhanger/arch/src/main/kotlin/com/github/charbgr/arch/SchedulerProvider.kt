package com.github.charbgr.arch

import io.reactivex.Scheduler

interface SchedulerProvider {
  fun ui(): Scheduler
  fun io(): Scheduler
}

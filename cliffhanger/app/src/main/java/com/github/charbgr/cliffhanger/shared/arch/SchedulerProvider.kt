package com.github.charbgr.cliffhanger.shared.arch

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

interface SchedulerProvider {
  fun ui(): Scheduler
  fun io(): Scheduler

  object Default: SchedulerProvider {
    override fun ui(): Scheduler = AndroidSchedulers.mainThread()
    override fun io(): Scheduler = Schedulers.io()
  }
}

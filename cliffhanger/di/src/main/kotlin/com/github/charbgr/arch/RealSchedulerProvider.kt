package com.github.charbgr.arch

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal object RealSchedulerProvider : SchedulerProvider {
  override fun ui(): Scheduler = AndroidSchedulers.mainThread()
  override fun io(): Scheduler = Schedulers.io()
}

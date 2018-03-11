package com.github.charbgr.cliffhanger.shared.extensions

import com.github.charbgr.arch.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object AndroidSchedulerProvider : SchedulerProvider {
  override fun ui(): Scheduler = AndroidSchedulers.mainThread()
  override fun io(): Scheduler = Schedulers.io()
}

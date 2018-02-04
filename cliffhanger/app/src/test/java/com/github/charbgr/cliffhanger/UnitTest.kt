package com.github.charbgr.cliffhanger

import com.github.charbgr.cliffhanger.shared.arch.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

open class UnitTest {
  protected val fakeSchedulerProvider = object : SchedulerProvider {
    override fun ui(): Scheduler = Schedulers.trampoline()
    override fun io(): Scheduler = Schedulers.trampoline()
  }
}

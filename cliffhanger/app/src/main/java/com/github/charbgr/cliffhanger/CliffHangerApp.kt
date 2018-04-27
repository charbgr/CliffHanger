package com.github.charbgr.cliffhanger

import android.app.Application
import com.github.charbgr.cliffhanger.shared.logger.CrashReportingTree
import timber.log.Timber
import timber.log.Timber.DebugTree


class CliffHangerApp : Application() {

  override fun onCreate() {
    super.onCreate()

    initializeLogger()
  }

  private fun initializeLogger() {
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    } else {
      Timber.plant(CrashReportingTree())
    }
  }

}

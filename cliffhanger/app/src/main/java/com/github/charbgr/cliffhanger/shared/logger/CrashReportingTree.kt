package com.github.charbgr.cliffhanger.shared.logger

import android.util.Log
import timber.log.Timber

class CrashReportingTree : Timber.Tree() {
  override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
    if ((priority == Log.VERBOSE) or (priority == Log.DEBUG)) {
      return
    }

    //TODO ADD CRASH REPORTING PLATFORM
  }
}
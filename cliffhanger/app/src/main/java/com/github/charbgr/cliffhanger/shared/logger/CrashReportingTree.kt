package com.github.charbgr.cliffhanger.shared.logger

import android.util.Log
import timber.log.Timber

class CrashReportingTree: Timber.Tree() {
  override fun log(priority: Int, tag: String?, message: String?, t: Throwable?) {
    if(priority == (Log.VERBOSE or Log.DEBUG)) {
      Log.d("priority", priority.toString())
      Log.d("log ", Log.VERBOSE.toString())
      Log.d("log ", Log.DEBUG.toString())
      return
    }
  }
}
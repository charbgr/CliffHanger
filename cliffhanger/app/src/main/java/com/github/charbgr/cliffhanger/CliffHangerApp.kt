package com.github.charbgr.cliffhanger

import android.app.Application
import com.github.charbgr.cliffhanger.di.DaggerAppComponent
import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.shared.logger.CrashReportingTree
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject


class CliffHangerApp : Application() {

  @Inject
  internal lateinit var movie: Movie

  override fun onCreate() {
    super.onCreate()

    initializeDependencies()
    initializeLogger()
  }

  private fun initializeDependencies() {
    DaggerAppComponent.builder().app(this).build().inject(this)
  }

  private fun initializeLogger() {
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    } else {
      Timber.plant(CrashReportingTree())
    }
  }
}

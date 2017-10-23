package com.github.charbgr.cliffhanger

import android.app.Activity
import android.app.Application
import com.github.charbgr.cliffhanger.di.DaggerAppComponent
import com.github.charbgr.cliffhanger.shared.logger.CrashReportingTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject


class CliffHangerApp : Application(), HasActivityInjector {

  @Inject internal lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

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

  override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}

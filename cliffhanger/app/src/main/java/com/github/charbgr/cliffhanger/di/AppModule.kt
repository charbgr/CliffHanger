package com.github.charbgr.cliffhanger.di

import android.content.Context
import com.github.charbgr.cliffhanger.CliffHangerApp
import dagger.Module
import dagger.Provides

@Module
class AppModule {

  @Provides
  fun provideContext(application: CliffHangerApp): Context {
    return application.applicationContext
  }

}

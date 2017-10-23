package com.github.charbgr.cliffhanger.di

import com.github.charbgr.cliffhanger.CliffHangerApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = arrayOf(
    AppModule::class,
    AndroidInjectionModule::class
))
interface AppComponent {

  @Component.Builder
  interface Builder {
    fun build(): AppComponent

    @BindsInstance
    fun app(app: CliffHangerApp): Builder
  }

  fun inject(app: CliffHangerApp)
}

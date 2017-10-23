package com.github.charbgr.cliffhanger.features.home.di

import com.github.charbgr.cliffhanger.features.home.HomeController
import com.github.charbgr.cliffhanger.features.home.arch.DefaultHomeInteractor
import com.github.charbgr.cliffhanger.features.home.arch.HomeInteractor
import com.github.charbgr.cliffhanger.features.home.arch.HomePresenter
import com.github.charbgr.cliffhanger.features.home.arch.HomeUiBinder
import com.github.charbgr.cliffhanger.features.home.arch.HomeView
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers

@Module
object HomeModule {

  @Provides
  @JvmStatic
  internal fun providesHomeView(homeController: HomeController): HomeView {
    return homeController
  }

  @Provides
  @JvmStatic
  internal fun providesHomeBinder(homeController: HomeController): HomeUiBinder {
    return HomeUiBinder(homeController)
  }

  @Provides
  @JvmStatic
  internal fun providesHomeInteractor(): HomeInteractor {
    return DefaultHomeInteractor()
  }

  @Provides
  @JvmStatic
  internal fun providesHomePresenter(homeInteractor: HomeInteractor): HomePresenter {
    return HomePresenter(
        interactor = homeInteractor,
        scheduler = AndroidSchedulers.mainThread()
    )
  }
}

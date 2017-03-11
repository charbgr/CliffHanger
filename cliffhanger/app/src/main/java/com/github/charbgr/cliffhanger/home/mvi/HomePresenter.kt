package com.github.charbgr.cliffhanger.home.mvi

import com.hannesdorfmann.mosby3.mvi.MviBasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomePresenter(
    private val interactor: HomeInteractor = DefaultHomeInteractor())
  : MviBasePresenter<HomeView, HomeViewModel>() {

  override fun bindIntents() {

    val bottonNavigationIntent = intent { it.bottomNavigationIntent() }
        .flatMap { interactor.loadData().map { HomeViewModel(false, true, it) } }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    subscribeViewState(bottonNavigationIntent, HomeView::render)
  }

}

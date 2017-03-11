package com.github.charbgr.cliffhanger.home.mvi

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable


interface HomeView : MvpView {
  fun bottomNavigationIntent(): Observable<Int>
  fun render(viewModel: HomeViewModel)
}

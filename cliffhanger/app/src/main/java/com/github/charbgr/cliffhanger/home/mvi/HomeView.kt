package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.home.NavigationItem
import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable


interface HomeView : MvpView {
  fun bottomNavigationIntent(): Observable<NavigationItem>
  fun render(viewModel: HomeViewModel)
}

package com.github.charbgr.cliffhanger.home.mvi

import com.hannesdorfmann.mosby3.mvp.MvpView
import io.reactivex.Observable


interface HomeView : MvpView {
  fun topRatedClickIntent(): Observable<Boolean>
  fun nowPlayingClickIntent(): Observable<Boolean>
  fun watchlistClickIntent(): Observable<Boolean>
  fun popularClickIntent(): Observable<Boolean>
  fun upcomingClickIntent(): Observable<Boolean>
  fun render(viewModel: HomeViewModel)
}

package com.github.charbgr.cliffhanger.home.mvi

import com.github.charbgr.cliffhanger.shared.arch.View
import io.reactivex.Observable

interface HomeView : View {
  fun topRatedClickIntent(): Observable<Boolean>
  fun nowPlayingClickIntent(): Observable<Boolean>
  fun watchlistClickIntent(): Observable<Boolean>
  fun popularClickIntent(): Observable<Boolean>
  fun upcomingClickIntent(): Observable<Boolean>
  fun render(viewModel: HomeViewModel)
}

package com.github.charbgr.cliffhanger.feature.browser.arch.interactor

import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.domain.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.domain.MovieCategory.Popular
import com.github.charbgr.cliffhanger.domain.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.domain.MovieCategory.Upcoming

object MovieBrowserInteractorFactory {

  fun createInteractor(movieCategory: MovieCategory): MovieBrowserInteractor {
    return when (movieCategory) {
      is TopRated -> TopRatedInteractor()
      is NowPlaying -> NowPlayingInteractor()
      is Popular -> PopularInteractor()
      is Upcoming -> UpcomingInteractor()
      else -> {
        NanInteractor()
      }
    }
  }
}

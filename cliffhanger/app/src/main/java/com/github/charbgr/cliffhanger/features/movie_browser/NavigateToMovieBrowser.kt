package com.github.charbgr.cliffhanger.features.movie_browser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.shared.commands.ControllerNavigationCommand

class NavigateToMovieBrowser(context: Context,
    private val movieCategory: MovieCategory) : ControllerNavigationCommand(context) {

  override fun getController(layoutInflater: LayoutInflater): View {
    return MovieBrowserController.inflateWith(movieCategory, layoutInflater)
  }
}

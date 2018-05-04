package com.github.charbgr.cliffhanger.feature.browser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.shared.commands.ControllerNavigationCommand

class NavigateToBrowser(
  context: Context,
  val movieCategory: MovieCategory
) : ControllerNavigationCommand(context) {

  override fun getController(layoutInflater: LayoutInflater): View {
    return BrowserController.inflateWith(movieCategory,
        layoutInflater)
  }
}

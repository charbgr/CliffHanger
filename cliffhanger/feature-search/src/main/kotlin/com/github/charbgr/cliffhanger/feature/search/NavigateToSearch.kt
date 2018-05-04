package com.github.charbgr.cliffhanger.feature.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.github.charbgr.cliffhanger.shared.commands.ControllerNavigationCommand

class NavigateToSearch(context: Context) : ControllerNavigationCommand(context) {

  override fun getController(layoutInflater: LayoutInflater): View =
      SearchController.inflateWith(inflater = layoutInflater)
}

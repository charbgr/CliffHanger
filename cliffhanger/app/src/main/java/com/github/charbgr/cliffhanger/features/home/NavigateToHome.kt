package com.github.charbgr.cliffhanger.features.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.github.charbgr.cliffhanger.shared.commands.ControllerNavigationCommand

class NavigateToHome(context: Context): ControllerNavigationCommand(context) {

  override fun getController(layoutInflater: LayoutInflater): View {
    return HomeController.inflateWith(layoutInflater)
  }

}

package com.github.charbgr.cliffhanger.shared.commands

import android.content.Context
import android.support.annotation.IdRes
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import timber.log.Timber

abstract class ControllerNavigationCommand(protected val context: Context) : Command {

  @IdRes
  private var containerId: Int = 0

  fun withContainer(@IdRes containerId: Int) = apply {
    this.containerId = containerId
  }

  protected abstract fun getController(layoutInflater: LayoutInflater): View

  override fun execute() {
    if (context !is AppCompatActivity) {
      Timber.d("Context must be an Activity context")
      return
    }


    val containerViewGroup: ViewGroup? = context.findViewById(containerId) as? ViewGroup
    if(containerViewGroup == null) {
      Timber.d("Container is not a ViewGroup")
      return
    }

    val layoutInflater = LayoutInflater.from(context)
    containerViewGroup.addView(getController(layoutInflater))
  }

}

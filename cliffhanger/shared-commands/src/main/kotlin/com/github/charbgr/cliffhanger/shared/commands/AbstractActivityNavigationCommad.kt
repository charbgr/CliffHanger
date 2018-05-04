package com.github.charbgr.cliffhanger.shared.commands

import android.content.Context
import android.content.Intent
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.util.Pair
import android.support.v7.app.AppCompatActivity
import android.view.View

/**
 * This class contains boilerplate and helper methods for
 * - handling stack of the activities
 * - clearing the current activity
 * - navigate to wanted activity screen
 */
abstract class AbstractActivityNavigationCommand : Command {

  private val intentFlags: MutableList<Int> = mutableListOf()
  private var shouldCloseCurrentActivity: Boolean = false
  private val sharedTransitions: MutableList<Pair<View, String>> = mutableListOf()

  init {
    if (!isActivityContext()) {
      intentFlags.add(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
  }

  /**
   * Adds a pair for shared transition if [addTransition] is true.
   * Pair has as key the view and value a transition name.
   */
  fun addSharedTransition(
    addTransition: Boolean = true,
    view: View?,
    transitionName: String?
  ) = apply {
    if (addTransition) {
      val pair: Pair<View, String> = Pair.create(view, transitionName)
      sharedTransitions.add(pair)
    }
  }

  fun closeCurrentActivity() = apply {
    shouldCloseCurrentActivity = true
  }

  fun clearStack(): AbstractActivityNavigationCommand = apply {
    intentFlags.add(Intent.FLAG_ACTIVITY_CLEAR_TASK)
  }

  private fun isActivityContext(): Boolean = getContext() is AppCompatActivity

  private fun navigate(intent: Intent) {
    if (sharedTransitions.isEmpty()) {
      getContext().startActivity(intent)
    } else {
      val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
          getContext() as AppCompatActivity,
          *sharedTransitions.toTypedArray()
      )
      getContext().startActivity(intent, options.toBundle())
    }
  }

  abstract fun getContext(): Context
  abstract fun createIntent(): Intent

  override fun execute() {
    val intent = createIntent()

    intentFlags.forEach {
      intent.addFlags(intent.flags and it)
    }

    navigate(intent)

    if (shouldCloseCurrentActivity) {
      (getContext() as AppCompatActivity).finish()
    }
  }
}

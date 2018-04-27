package com.github.charbgr.cliffhanger.feature.error

import android.content.Context
import android.content.Intent
import com.github.charbgr.cliffhanger.shared.commands.AbstractActivityNavigationCommand

class NavigateToError(private val context: Context) : AbstractActivityNavigationCommand() {
  override fun getContext(): Context = context
  override fun createIntent(): Intent = Intent(context, ErrorActivity::class.java)
}

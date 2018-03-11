package com.github.charbgr.cliffhanger.shared.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.render(layoutId: Int, attachToRoot: Boolean = false): View {
  return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

fun View.visibleOrGone(shouldBeVisible: Boolean) {
  if (shouldBeVisible) {
    this.visible()
  } else {
    this.gone()
  }
}

fun View.visible() {
  this.visibility = View.VISIBLE
}

fun View.gone() {
  this.visibility = View.GONE
}

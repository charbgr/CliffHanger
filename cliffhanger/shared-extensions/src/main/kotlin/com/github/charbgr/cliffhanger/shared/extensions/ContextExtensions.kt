package com.github.charbgr.cliffhanger.shared.extensions

import android.content.Context
import android.util.DisplayMetrics

fun Context.dpToPx(dp: Float): Float {
  val metrics = resources.displayMetrics
  val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
  return px
}

fun Context.pxToDp(px: Float): Float {
  val metrics = resources.displayMetrics
  val dp = px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
  return dp
}

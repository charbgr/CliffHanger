package com.github.charbgr.cliffhanger.shared.views.recyclerview

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.view.View

class SpacingItemDecorator(val spacing: Int) : RecyclerView.ItemDecoration() {

  companion object {

    private fun dpToPx(context: Context, dp: Float): Float {
      val metrics = context.resources.displayMetrics
      val px = dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
      return px
    }

    fun create(context: Context, dps: Float): SpacingItemDecorator {
      return SpacingItemDecorator(dpToPx(context, dps).toInt())
    }
  }

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
      state: RecyclerView.State) {
    outRect.set(spacing, spacing, spacing, spacing)
  }

}

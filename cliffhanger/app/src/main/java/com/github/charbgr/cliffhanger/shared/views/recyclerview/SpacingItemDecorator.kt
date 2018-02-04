package com.github.charbgr.cliffhanger.shared.views.recyclerview

import android.content.Context
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.github.charbgr.cliffhanger.shared.extensions.dpToPx

class SpacingItemDecorator(val spacing: Int) : RecyclerView.ItemDecoration() {

  companion object {
    fun create(context: Context, dps: Float): SpacingItemDecorator {
      return SpacingItemDecorator(context.dpToPx(dps).toInt())
    }
  }

  override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView,
      state: RecyclerView.State) {
    outRect.set(spacing, spacing, spacing, spacing)
  }

}

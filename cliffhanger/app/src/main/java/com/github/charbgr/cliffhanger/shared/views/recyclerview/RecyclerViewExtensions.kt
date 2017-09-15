package com.github.charbgr.cliffhanger.shared.views.recyclerview

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration

fun RecyclerView.addSpacing(dps: Float): ItemDecoration {
  val decorator = SpacingItemDecorator.create(this.context, dps)
  addItemDecoration(decorator)
  return decorator
}

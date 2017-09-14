package com.github.charbgr.cliffhanger.shared.views.recyclerview

import android.support.v7.widget.RecyclerView

fun RecyclerView.addSpacing(dps: Float) {
  addItemDecoration(SpacingItemDecorator.create(this.context, dps))
}

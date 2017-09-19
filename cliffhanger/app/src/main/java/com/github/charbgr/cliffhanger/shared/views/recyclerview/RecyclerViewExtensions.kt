package com.github.charbgr.cliffhanger.shared.views.recyclerview

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import io.reactivex.Observable

fun RecyclerView.addSpacing(dps: Float): ItemDecoration {
  val decorator = SpacingItemDecorator.create(this.context, dps)
  addItemDecoration(decorator)
  return decorator
}

fun RecyclerView.infiniteScrollIntent(layoutManager: LinearLayoutManager, threshold: Int = 3)
    : Observable<RecyclerViewScrollEvent> {
  return this.scrollEvents()
      .filter { this.childCount > 0 && it.dy() > 0 }
      .filter {
        val visibleItemCount = this.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        totalItemCount - visibleItemCount <= firstVisibleItem + threshold
      }
      .distinctUntilChanged()
}
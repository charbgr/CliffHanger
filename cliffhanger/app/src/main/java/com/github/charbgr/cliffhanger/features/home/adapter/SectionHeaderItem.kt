package com.github.charbgr.cliffhanger.features.home.adapter

import android.content.Context
import android.support.annotation.StringRes
import com.github.charbgr.cliffhanger.features.home.adapter.MovieGroupItem.ViewTypes
import com.github.charbgr.cliffhanger.shared.adapter.movies.MovieAdapterItem

class SectionHeaderItem(val title: String, val movieItems: List<MovieAdapterItem>) : MovieGroupItem {

  companion object {
    fun create(context: Context, @StringRes stringRes: Int, movieItems: List<MovieAdapterItem>): SectionHeaderItem {
      return SectionHeaderItem(context.getString(stringRes), movieItems)
    }
  }

  override fun getItemViewType(): Int = ViewTypes.SECTION
}

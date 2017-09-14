package com.github.charbgr.cliffhanger.features.home.movies

import android.content.Context
import android.support.annotation.StringRes
import com.github.charbgr.cliffhanger.features.home.movies.MovieAdapterItem.ViewTypes

class SectionHeaderItem(val title: String) : MovieAdapterItem {

  companion object {
    fun create(context: Context, @StringRes stringRes: Int): SectionHeaderItem {
      return SectionHeaderItem(context.getString(stringRes))
    }
  }

  override fun getItemViewType(): Int = ViewTypes.SECTION
  override fun getSpanSize(position: Int): Int = 2
}

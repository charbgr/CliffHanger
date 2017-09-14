package com.github.charbgr.cliffhanger.features.home.group

import android.content.Context
import android.support.annotation.StringRes
import com.github.charbgr.cliffhanger.features.home.group.MovieGroupItem.ViewTypes

class SectionHeaderItem(val title: String) : MovieGroupItem {

  companion object {
    fun create(context: Context, @StringRes stringRes: Int): SectionHeaderItem {
      return SectionHeaderItem(
          context.getString(stringRes))
    }
  }

  override fun getItemViewType(): Int = ViewTypes.SECTION
}

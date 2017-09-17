package com.github.charbgr.cliffhanger.features.home.adapter

import android.content.Context
import android.support.annotation.StringRes
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.adapter.MovieGroupItem.ViewTypes
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.domain.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.domain.MovieCategory.Popular
import com.github.charbgr.cliffhanger.domain.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.domain.MovieCategory.Upcoming

class SectionHeaderItem(val title: String) : MovieGroupItem {

  companion object {

    fun create(context: Context, movieCategory: MovieCategory): SectionHeaderItem {
      return when(movieCategory) {
        TopRated -> create(context, R.string.movie_category_top_rated)
        NowPlaying -> create(context, R.string.movie_category_now_playing)
        Popular -> create(context, R.string.movie_category_popular)
        Upcoming -> create(context, R.string.movie_category_upcoming)
        else -> {
          SectionHeaderItem("")
        }
      }
    }

    fun create(context: Context, @StringRes stringRes: Int): SectionHeaderItem {
      return SectionHeaderItem(context.getString(stringRes))
    }
  }

  override fun getItemViewType(): Int = ViewTypes.SECTION
}

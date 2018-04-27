package com.github.charbgr.cliffhanger.feature.home.adapter

import android.content.Context
import charbgr.github.com.feature_home.R
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.domain.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.domain.MovieCategory.Popular
import com.github.charbgr.cliffhanger.domain.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.domain.MovieCategory.Upcoming
import com.github.charbgr.cliffhanger.feature.home.adapter.MovieGroupItem.ViewTypes

class SectionHeaderItem(val context: Context, val movieCategory: MovieCategory) : MovieGroupItem {

  val title: String

  init {
    title = when (movieCategory) {
      is TopRated -> context.getString(R.string.movie_category_top_rated)
      is NowPlaying -> context.getString(R.string.movie_category_now_playing)
      is Popular -> context.getString(R.string.movie_category_popular)
      is Upcoming -> context.getString(R.string.movie_category_upcoming)
      else -> ""
    }
  }

  override fun getItemViewType(): Int = ViewTypes.SECTION
}

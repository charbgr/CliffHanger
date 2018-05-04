package com.github.charbgr.cliffhanger.feature.browser.arch

import android.content.Context
import com.github.charbgr.cliffhanger.domain.MiniMovie
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.domain.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.domain.MovieCategory.Popular
import com.github.charbgr.cliffhanger.domain.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.domain.MovieCategory.Upcoming
import com.github.charbgr.cliffhanger.feature.browser.R

data class BrowserViewModel(
  val movieCategory: MovieCategory,
  val isLoading: Boolean,
  val movies: List<MiniMovie>?,
  val page: Int,
  val throwable: Throwable?
) {

  companion object {
    fun initial(movieCategory: MovieCategory): BrowserViewModel {
      return BrowserViewModel(movieCategory, false, null, 0,
          null)
    }
  }

  fun hasError(): Boolean = throwable != null
  fun hasData(): Boolean = movies != null

  fun screenTitle(context: Context): String {
    return when (movieCategory) {
      is TopRated -> context.getString(R.string.movie_category_top_rated)
      is NowPlaying -> context.getString(R.string.movie_category_now_playing)
      is Popular -> context.getString(R.string.movie_category_popular)
      is Upcoming -> context.getString(R.string.movie_category_upcoming)
      else -> ""
    }
  }
}

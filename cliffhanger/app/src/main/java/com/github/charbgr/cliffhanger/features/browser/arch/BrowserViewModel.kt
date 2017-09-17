package com.github.charbgr.cliffhanger.features.browser.arch

import android.content.Context
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.domain.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.domain.MovieCategory.Popular
import com.github.charbgr.cliffhanger.domain.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.domain.MovieCategory.Upcoming
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange
import com.github.charbgr.cliffhanger.network.tmdb.dao.MovieResults

data class BrowserViewModel(
    val movieCategory: MovieCategory,
    val isLoading: Boolean,
    val movieResults: MovieResults?,
    val throwable: Throwable?,
    val lastPartialChange: PartialChange
) {

  companion object {
    fun initial(movieCategory: MovieCategory): BrowserViewModel {
      return BrowserViewModel(movieCategory, false, null, null, PartialChange.Init)
    }
  }


  fun hasError(): Boolean = throwable != null
  fun hasData(): Boolean = movieResults != null

  fun screenTitle(context: Context): String {
    return when(movieCategory) {
      is TopRated -> context.getString(R.string.movie_category_top_rated)
      is NowPlaying -> context.getString(R.string.movie_category_now_playing)
      is Popular -> context.getString(R.string.movie_category_popular)
      is Upcoming -> context.getString(R.string.movie_category_upcoming)
      else -> ""
    }
  }
}

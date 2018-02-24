package com.github.charbgr.cliffhanger.features.detail

import android.content.Context
import android.content.Intent
import com.github.charbgr.cliffhanger.domain.MiniMovie
import com.github.charbgr.cliffhanger.shared.commands.AbstractActivityNavigationCommand

class NavigateToMovieDetail(
    private val context: Context,
    private val movieId: Int
) : AbstractActivityNavigationCommand() {

  constructor(context: Context, movie: MiniMovie) : this(context, movie.tmdbId)

  override fun getContext(): Context = context
  override fun createIntent(): Intent {
    return Intent(context, MovieDetailActivity::class.java).apply {
      putExtra(MovieDetailActivity.Contract.MOVIE_ID_EXTRA, movieId)
    }
  }

}

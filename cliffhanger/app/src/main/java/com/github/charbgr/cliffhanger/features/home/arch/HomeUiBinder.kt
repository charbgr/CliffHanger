package com.github.charbgr.cliffhanger.features.home.arch

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.view.MenuItem
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.HomeController
import com.github.charbgr.cliffhanger.features.home.movies.MovieAdapter
import com.github.charbgr.cliffhanger.features.home.movies.MovieItem
import com.github.charbgr.cliffhanger.shared.transformers.movie.transformToMovies
import com.jakewharton.rxbinding2.support.design.widget.itemSelections
import io.reactivex.Observable
import kotlinx.android.synthetic.main.controller_home.view.bottom_navigation
import kotlinx.android.synthetic.main.controller_home.view.movie_list
import timber.log.Timber

open class HomeUiBinder(internal val controller: HomeController) : HomeView {

  private val bottomNavigationSelection: Observable<MenuItem> by lazy {
    controller.bottom_navigation.itemSelections().share()
  }

  val movieAdapter: MovieAdapter by lazy {
    MovieAdapter()
  }

  fun onFinishInflate() {
    with(controller.movie_list) {
      val gridColumns = context.resources.getInteger(R.integer.home_grid_columns)
      val lm = GridLayoutManager(context, gridColumns)
      lm.spanSizeLookup = object : SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
          return movieAdapter.getItemAt(position)?.getSpanSize(position) ?: gridColumns
        }
      }

      layoutManager = lm
      adapter = movieAdapter
    }
  }

  override fun topRatedClickIntent(): Observable<Boolean> = bottomNavigationSelection
      .filter { it.itemId == R.id.bottom_navigation_category_top_rated }
      .map { true }

  override fun nowPlayingClickIntent(): Observable<Boolean> = bottomNavigationSelection
      .filter { it.itemId == R.id.bottom_navigation_category_now_playing }
      .map { true }

  override fun watchlistClickIntent(): Observable<Boolean> = bottomNavigationSelection
      .filter { it.itemId == R.id.bottom_navigation_watchlist }
      .map { true }

  override fun popularClickIntent(): Observable<Boolean> = bottomNavigationSelection
      .filter { it.itemId == R.id.bottom_navigation_category_popular }
      .map { true }

  override fun upcomingClickIntent(): Observable<Boolean> = bottomNavigationSelection
      .filter { it.itemId == R.id.bottom_navigation_category_upcoming }
      .map { true }

  override fun render(viewModel: HomeViewModel) {
    Timber.d("viewmodel receiver " + viewModel)

    viewModel.movieResults?.results?.transformToMovies()?.let {
      controller.movie_list?.scrollToPosition(0)
      movieAdapter.setItems(it.map { MovieItem(it) })
    }
  }

}

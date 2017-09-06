package com.github.charbgr.cliffhanger.features.home

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.movies.MovieAdapter
import com.github.charbgr.cliffhanger.features.home.arch.HomePresenter
import com.github.charbgr.cliffhanger.features.home.arch.HomeView
import com.github.charbgr.cliffhanger.features.home.arch.HomeViewModel
import com.github.charbgr.cliffhanger.shared.transformers.movie.transformToMovies
import com.jakewharton.rxbinding2.support.design.widget.itemSelections
import io.reactivex.Observable
import kotlinx.android.synthetic.main.controller_home.view.bottom_navigation
import kotlinx.android.synthetic.main.controller_home.view.movie_list
import timber.log.Timber

class HomeController : ConstraintLayout, HomeView {

  companion object {
    fun inflateWith(inflater: LayoutInflater, parent: ViewGroup? = null,
        attachToRoot: Boolean = false): HomeController {
      return inflater.inflate(R.layout.controller_home, parent, attachToRoot) as HomeController
    }
  }

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  private val bottomNavigationSelection: Observable<MenuItem> by lazy {
    bottom_navigation.itemSelections().share()
  }

  private val movieAdapter: MovieAdapter by lazy {
    MovieAdapter()
  }

  private val presenter: HomePresenter by lazy {
    HomePresenter()
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    with(rootView.movie_list) {
      layoutManager = LinearLayoutManager(context)
      adapter = movieAdapter
    }

    presenter.init(this)
    presenter.bindIntents()
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
      rootView?.movie_list?.scrollToPosition(0)
      movieAdapter.setMovies(it)
    }

  }
}
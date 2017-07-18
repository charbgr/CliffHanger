package com.github.charbgr.cliffhanger.home

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.home.movies.MovieAdapter
import com.github.charbgr.cliffhanger.home.mvi.HomeView
import com.github.charbgr.cliffhanger.home.mvi.HomeViewModel
import com.github.charbgr.cliffhanger.search.SearchController
import com.github.charbgr.cliffhanger.shared.extensions.render
import com.github.charbgr.cliffhanger.shared.transformers.movie.transformToMovies
import com.jakewharton.rxbinding2.support.design.widget.itemSelections
import io.reactivex.Observable
import kotlinx.android.synthetic.main.controller_home.view.bottom_navigation
import kotlinx.android.synthetic.main.controller_home.view.movie_list
import kotlinx.android.synthetic.main.controller_home.view.search
import timber.log.Timber

class HomeController : Controller(), HomeView {

  private val bottomNavigationSelection: Observable<MenuItem> by lazy {
    view!!.bottom_navigation.itemSelections().share()
  }

  private val movieAdapter: MovieAdapter by lazy {
    MovieAdapter()
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
    val rootView: View = container.render(R.layout.controller_home)

    rootView.search.setOnClickListener {
      router.pushController(RouterTransaction.with(SearchController()))
    }

    with(rootView.movie_list) {
      layoutManager = LinearLayoutManager(context)
      adapter = movieAdapter
    }

    return rootView
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
      view?.movie_list?.scrollToPosition(0)
      movieAdapter.setMovies(it)
    }

  }
}
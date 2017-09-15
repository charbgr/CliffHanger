package com.github.charbgr.cliffhanger.features.home.arch

import android.support.v7.widget.LinearLayoutManager
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.HomeController
import com.github.charbgr.cliffhanger.features.home.adapter.MovieCarouselItem
import com.github.charbgr.cliffhanger.features.home.adapter.MovieGroupAdapter
import com.github.charbgr.cliffhanger.features.home.adapter.SectionHeaderItem
import com.github.charbgr.cliffhanger.shared.adapter.movies.MovieListViewModel
import com.github.charbgr.cliffhanger.shared.transformers.movie.transformToMovies
import kotlinx.android.synthetic.main.controller_home.view.movie_list
import timber.log.Timber

open class HomeUiBinder(internal val controller: HomeController) : HomeView {

  val movieAdapter: MovieGroupAdapter by lazy {
    MovieGroupAdapter(controller.movie_list.recycledViewPool)
  }

  fun onFinishInflate() {
    with(controller.movie_list) {
      layoutManager = LinearLayoutManager(context)
      adapter = movieAdapter
    }
  }

  override fun render(viewModel: HomeViewModel) {
    Timber.d("viewmodel receiver " + viewModel)

    if (viewModel.topRated != null) {
      val movies = viewModel.topRated.results.transformToMovies().map { MovieListViewModel(it) }
      val section = SectionHeaderItem.create(controller.context, R.string.movie_category_top_rated,
          movies)
      val carouselItem = MovieCarouselItem(movies)

      movieAdapter.addItems(listOf(section, carouselItem))
    }

    if (viewModel.nowPlaying != null) {
      val movies = viewModel.nowPlaying.results.transformToMovies().map { MovieListViewModel(it) }
      val section = SectionHeaderItem.create(controller.context,
          R.string.movie_category_now_playing, movies)
      val carouselItem = MovieCarouselItem(movies)

      movieAdapter.addItems(listOf(section, carouselItem))
    }

    if (viewModel.popular != null) {
      val movies = viewModel.popular.results.transformToMovies().map { MovieListViewModel(it) }
      val section = SectionHeaderItem.create(controller.context, R.string.movie_category_popular,
          movies)
      val carouselItem = MovieCarouselItem(movies)

      movieAdapter.addItems(listOf(section, carouselItem))
    }

    if (viewModel.upcoming != null) {
      val movies = viewModel.upcoming.results.transformToMovies().map { MovieListViewModel(it) }
      val section = SectionHeaderItem.create(controller.context, R.string.movie_category_upcoming,
          movies)
      val carouselItem = MovieCarouselItem(movies)

      movieAdapter.addItems(listOf(section, carouselItem))
    }
  }

}

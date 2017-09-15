package com.github.charbgr.cliffhanger.features.home.arch

import android.support.v7.widget.LinearLayoutManager
import com.github.charbgr.cliffhanger.features.home.HomeController
import com.github.charbgr.cliffhanger.features.home.adapter.MovieCarouselItem
import com.github.charbgr.cliffhanger.features.home.adapter.MovieGroupAdapter
import com.github.charbgr.cliffhanger.features.home.adapter.MovieGroupItem
import com.github.charbgr.cliffhanger.features.home.adapter.SectionHeaderItem
import com.github.charbgr.cliffhanger.features.home.arch.state.MovieCategory
import com.github.charbgr.cliffhanger.features.home.arch.state.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.features.home.arch.state.MovieCategory.Popular
import com.github.charbgr.cliffhanger.features.home.arch.state.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.features.home.arch.state.MovieCategory.Upcoming
import com.github.charbgr.cliffhanger.shared.adapter.movies.MovieListViewModel
import com.github.charbgr.cliffhanger.shared.transformers.movie.transformToMovies
import kotlinx.android.synthetic.main.controller_home.view.movie_list

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

  private fun convertToItems(movieCategory: MovieCategory,
      movieCategoryViewModel: CategoryViewModel): List<MovieGroupItem> {

    val items: MutableList<MovieGroupItem> = mutableListOf()

    if (movieCategoryViewModel.isLoading) {
      //TODO
    }

    if (movieCategoryViewModel.hasData()) {
      items.add(SectionHeaderItem.create(controller.context, movieCategory))
      items.add(MovieCarouselItem(
          movieCategoryViewModel.movieResults!!.results.transformToMovies().map {
            MovieListViewModel(it)
          }))
    }

    if (movieCategoryViewModel.hasError()) {
      //TODO
    }

    return items
  }

  override fun render(viewModel: HomeViewModel) {
    val movieCategory = viewModel.currentPartialChange.movieCategory

    when (movieCategory) {
      is TopRated -> {
        movieAdapter.addItems(convertToItems(movieCategory, viewModel.topRated))
      }
      is NowPlaying -> {
        movieAdapter.addItems(convertToItems(movieCategory, viewModel.nowPlaying))
      }
      is Popular -> {
        movieAdapter.addItems(convertToItems(movieCategory, viewModel.popular))
      }
      is Upcoming -> {
        movieAdapter.addItems(convertToItems(movieCategory, viewModel.upcoming))
      }
      else -> {

      }
    }

    movieAdapter.notifyDataSetChanged()
  }
}

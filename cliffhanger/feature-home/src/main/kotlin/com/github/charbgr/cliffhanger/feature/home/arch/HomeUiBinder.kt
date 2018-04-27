package com.github.charbgr.cliffhanger.feature.home.arch

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.domain.MovieCategory.NowPlaying
import com.github.charbgr.cliffhanger.domain.MovieCategory.Popular
import com.github.charbgr.cliffhanger.domain.MovieCategory.TopRated
import com.github.charbgr.cliffhanger.domain.MovieCategory.Upcoming
import com.github.charbgr.cliffhanger.feature.error.NavigateToError
import com.github.charbgr.cliffhanger.feature.home.HomeController
import com.github.charbgr.cliffhanger.feature.home.adapter.MovieCarouselItem
import com.github.charbgr.cliffhanger.feature.home.adapter.MovieGroupAdapter
import com.github.charbgr.cliffhanger.feature.home.adapter.MovieGroupItem
import com.github.charbgr.cliffhanger.feature.home.adapter.SectionHeaderItem
import com.github.charbgr.cliffhanger.feature.search.NavigateToSearch
import com.github.charbgr.cliffhanger.shared.movies.MovieListViewModel

open class HomeUiBinder(internal val controller: HomeController) : HomeView {

  val movieAdapter: MovieGroupAdapter by lazy {
    MovieGroupAdapter(controller.movieList.recycledViewPool)
  }

  fun onFinishInflate() {
    with(controller.movieList) {
      layoutManager = LinearLayoutManager(context)
      adapter = movieAdapter
    }

    controller.search.setOnClickListener {
      NavigateToSearch(it.context)
          .withContainer(((controller as ConstraintLayout).parent as ViewGroup).id)
          .execute()
    }
  }

  private fun convertToItems(movieCategory: MovieCategory,
      movieCategoryViewModel: CategoryViewModel): List<MovieGroupItem> {

    val items: MutableList<MovieGroupItem> = mutableListOf()

    if (movieCategoryViewModel.isLoading) {
      //TODO
    }

    if (movieCategoryViewModel.hasData()) {
      items.add(SectionHeaderItem(controller.context, movieCategory))
      items.add(MovieCarouselItem(movieCategoryViewModel.movies!!.map { MovieListViewModel(it) }))
    }

    if (movieCategoryViewModel.hasError()) {
      NavigateToError(controller.context).execute()
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

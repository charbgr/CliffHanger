package com.github.charbgr.cliffhanger.features.home.arch

import android.support.v7.widget.LinearLayoutManager
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.HomeController
import com.github.charbgr.cliffhanger.features.home.adapter.MovieCarouselItem
import com.github.charbgr.cliffhanger.features.home.adapter.MovieGroupAdapter
import com.github.charbgr.cliffhanger.features.home.adapter.MovieGroupItem
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

    val itemsToAdd: MutableList<MovieGroupItem> = mutableListOf()
    itemsToAdd.add(
        SectionHeaderItem.create(controller.context, R.string.movie_category_top_rated)
    )
    val movies = viewModel.movieResults?.results?.transformToMovies()?.map { MovieListViewModel(it) }
    movies?.let {
      itemsToAdd.add(MovieCarouselItem(it))
    }


    //TODO BIND REAL NOW PLAYING DATA
    itemsToAdd.add(
        SectionHeaderItem.create(controller.context, R.string.movie_category_now_playing)
    )

    movies?.let {
      itemsToAdd.add(MovieCarouselItem(it))
    }

    movieAdapter.setItems(itemsToAdd)
  }

}

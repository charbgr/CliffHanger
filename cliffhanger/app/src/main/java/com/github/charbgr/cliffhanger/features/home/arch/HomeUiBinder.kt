package com.github.charbgr.cliffhanger.features.home.arch

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.HomeController
import com.github.charbgr.cliffhanger.features.home.movies.MovieAdapter
import com.github.charbgr.cliffhanger.features.home.movies.MovieAdapterItem
import com.github.charbgr.cliffhanger.features.home.movies.MovieItem
import com.github.charbgr.cliffhanger.features.home.movies.SectionHeaderItem
import com.github.charbgr.cliffhanger.shared.transformers.movie.transformToMovies
import kotlinx.android.synthetic.main.controller_home.view.movie_list
import timber.log.Timber

open class HomeUiBinder(internal val controller: HomeController) : HomeView {

  val movieAdapter: MovieAdapter by lazy {
    MovieAdapter()
  }

  fun onFinishInflate() {
    with(controller.movie_list) {
      val gridColumns = context.resources.getInteger(R.integer.home_grid_columns)
      val lm = GridLayoutManager(context, gridColumns, GridLayoutManager.VERTICAL, false)
      lm.spanSizeLookup = object : SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
          return movieAdapter.getItemAt(position)?.getSpanSize(position) ?: gridColumns
        }
      }

      layoutManager = lm
      adapter = movieAdapter
    }
  }

  override fun render(viewModel: HomeViewModel) {
    Timber.d("viewmodel receiver " + viewModel)

    viewModel.movieResults?.results?.transformToMovies()?.let {
      controller.movie_list?.scrollToPosition(0)
      val itemsToAdd: MutableList<MovieAdapterItem> = mutableListOf()
      itemsToAdd.add(
          SectionHeaderItem.create(context = controller.context,
              stringRes = R.string.movie_category_top_rated))
      itemsToAdd.addAll(it.map { MovieItem(it) })
      movieAdapter.setItems(itemsToAdd)
    }
  }

}

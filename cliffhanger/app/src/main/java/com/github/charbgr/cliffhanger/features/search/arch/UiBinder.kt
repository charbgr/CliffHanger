package com.github.charbgr.cliffhanger.features.search.arch

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.error.NavigateToError
import com.github.charbgr.cliffhanger.features.search.SearchController
import com.github.charbgr.cliffhanger.features.search.arch.PartialChange.Failed
import com.github.charbgr.cliffhanger.features.search.arch.PartialChange.Success
import com.github.charbgr.cliffhanger.shared.adapter.movies.MovieAdapter
import com.github.charbgr.cliffhanger.shared.adapter.movies.MovieListViewModel
import com.github.charbgr.cliffhanger.shared.extensions.visibleOrGone
import com.jakewharton.rxbinding2.widget.textChanges
import io.reactivex.Observable
import java.util.concurrent.TimeUnit.MILLISECONDS

internal open class UiBinder(internal val controller: SearchController) : View {

  val movieAdapter: MovieAdapter by lazy {
    MovieAdapter()
  }

  fun onFinishInflate() {
    with(controller.movieList) {
      val gridColumns = context.resources.getInteger(R.integer.search_grid_columns)
      val lm = GridLayoutManager(context, gridColumns, GridLayoutManager.VERTICAL, false)
      lm.spanSizeLookup = object : SpanSizeLookup() {
        override fun getSpanSize(movieItemPosition: Int): Int {
          return movieAdapter.getItemAt(movieItemPosition)?.getSpanSize(1) ?: gridColumns
        }
      }
      layoutManager = lm
      adapter = movieAdapter
    }
  }

  fun bind(render: Pair<PartialChange, ViewModel>) {
    controller.progressBar.visibleOrGone(render.second.showLoader)

    if (render.first is Failed) {
      NavigateToError(context = controller.context)
          .execute()
    }

    if (render.first is Success) {
      movieAdapter.setItems(render.second.movies!!.map { MovieListViewModel(it) })
    }
  }

  override fun searchIntent(): Observable<CharSequence> =
      controller.search.textChanges()
          .skipInitialValue()
          .debounce(400, MILLISECONDS)

}


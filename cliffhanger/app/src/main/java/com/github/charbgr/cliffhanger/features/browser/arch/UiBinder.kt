package com.github.charbgr.cliffhanger.features.browser.arch

import android.support.v7.widget.LinearLayoutManager
import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.features.browser.BrowserController
import com.github.charbgr.cliffhanger.features.browser.adapter.BrowserAdapter
import com.github.charbgr.cliffhanger.features.browser.adapter.BrowserAdapterItem
import com.github.charbgr.cliffhanger.features.browser.adapter.MovieAdapterItem
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Failed
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Loaded
import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange.Loading
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

open class UiBinder(internal val controller: BrowserController) : BrowserView {

  private val loadData: PublishSubject<Any> = PublishSubject.create()
  private val adapter: BrowserAdapter by lazy {
    BrowserAdapter()
  }

  fun onFinishInflate() {
    with(controller.movieList) {
      layoutManager = LinearLayoutManager(context)
      adapter = this@UiBinder.adapter
    }
  }

  fun onAttachedToWindow() {  
    loadData.onNext(Any())
  }

  private fun toAdapterItems(movies: List<Movie>): List<BrowserAdapterItem> {
    return movies.map { MovieAdapterItem(it) }
  }

  private fun showLoader() {
    //TODO
  }

  private fun showError() {
    //TODO
  }

  override fun loadDataIntent(): Observable<Any> = loadData.share()

  override fun infiniteScrollIntent(): Observable<Any> = Observable.empty()

  override fun render(movieBrowserViewModel: BrowserViewModel) {
    val partialChange = movieBrowserViewModel.lastPartialChange
    when (partialChange) {
      is Loaded -> {
        adapter.addItems(toAdapterItems(partialChange.movieResults.toMovieList()))
      }
      is Loading -> {
        adapter.clearItems()
        controller.setScreenTitle(movieBrowserViewModel.screenTitle(controller.context))
        showLoader()
      }
      is Failed -> {
        showError()
      }
    }
  }


}

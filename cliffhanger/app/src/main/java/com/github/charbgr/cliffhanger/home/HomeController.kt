package com.github.charbgr.cliffhanger.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.home.mvi.HomePresenter
import com.github.charbgr.cliffhanger.home.mvi.HomeView
import com.github.charbgr.cliffhanger.home.mvi.HomeViewModel
import com.github.charbgr.cliffhanger.search.SearchController
import com.github.charbgr.cliffhanger.shared.extensions.render
import com.github.charbgr.cliffhanger.shared.extensions.toV2Observable
import com.hannesdorfmann.mosby3.MviController
import com.jakewharton.rxbinding.support.design.widget.itemSelections
import io.reactivex.Observable
import kotlinx.android.synthetic.main.controller_home.view.bottom_navigation
import kotlinx.android.synthetic.main.controller_home.view.search
import timber.log.Timber

class HomeController : MviController<HomeView, HomePresenter>(), HomeView {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
    val rootView: View = container.render(R.layout.controller_home)

    rootView.search.setOnClickListener {
      router.pushController(RouterTransaction.with(SearchController()))
    }

    return rootView
  }

  override fun createPresenter(): HomePresenter = HomePresenter()

  override fun bottomNavigationIntent(): Observable<Int> = view!!.bottom_navigation
      .itemSelections().toV2Observable().map { it.itemId }

  override fun render(viewModel: HomeViewModel) {
    Timber.d("viewmodel receiver " + viewModel)
  }
}
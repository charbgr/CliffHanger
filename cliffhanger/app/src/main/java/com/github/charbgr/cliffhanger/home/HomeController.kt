package com.github.charbgr.cliffhanger.home

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.shared.extensions.render
import com.github.charbgr.cliffhanger.shared.extensions.toV2Observable
import com.jakewharton.rxbinding.support.design.widget.itemSelections
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.controller_home.view.bottom_navigation
import timber.log.Timber

class HomeController : Controller() {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
    val rootView: View = container.render(R.layout.controller_home)

    initBottomNavigation(rootView)

    return rootView
  }

  private fun initBottomNavigation(rootView: View) {
    rootView
        .bottom_navigation
        .itemSelections()
        .toV2Observable()
        .subscribeWith(object : Observer<MenuItem> {
          override fun onError(e: Throwable?) {
          }

          override fun onComplete() {
          }

          override fun onNext(t: MenuItem?) {
            Timber.d("$t")
          }

          override fun onSubscribe(d: Disposable?) {
          }

        })

  }

}
package com.github.charbgr.cliffhanger.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.search.SearchController
import com.github.charbgr.cliffhanger.shared.render
import kotlinx.android.synthetic.main.controller_home.view.search

class HomeController : Controller() {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
    val rootView: View = container.render(R.layout.controller_home)

    rootView.search.setOnClickListener {
      router?.pushController(
          RouterTransaction
              .with(SearchController())
              .pushChangeHandler(FadeChangeHandler(false))
              .popChangeHandler(FadeChangeHandler())
              .tag("foo")
      )
    }


    return rootView
  }

}
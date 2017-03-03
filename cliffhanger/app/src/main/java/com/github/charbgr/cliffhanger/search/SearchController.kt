package com.github.charbgr.cliffhanger.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.shared.render

class SearchController : Controller() {
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
    val view: View = container.render(R.layout.controller_search)
    return view
  }
}
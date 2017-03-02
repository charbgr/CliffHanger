package com.github.charbgr.cliffhanger.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import com.github.charbgr.cliffhanger.R.layout

class HomeController : Controller() {


  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
    val rootView: View = inflater.inflate(layout.controller_home, container, false)
    return rootView
  }

}
package com.github.charbgr.cliffhanger.shared.arch

interface Presenter<in T : View> {
  fun init(view: T)
  fun destroy()
}
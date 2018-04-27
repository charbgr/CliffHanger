package com.github.charbgr.arch

interface Presenter<in T : View> {
  fun init(view: T)
  fun destroy()
}

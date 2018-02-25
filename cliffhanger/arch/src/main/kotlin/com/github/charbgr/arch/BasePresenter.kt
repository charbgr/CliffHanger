package com.github.charbgr.arch

import java.lang.ref.WeakReference

open class BasePresenter<T : View> : Presenter<T> {

  protected lateinit var viewWRef: WeakReference<T>

  override fun init(view: T) {
    viewWRef = WeakReference<T>(view)
  }

  override fun destroy() {
    viewWRef.clear()
  }
}

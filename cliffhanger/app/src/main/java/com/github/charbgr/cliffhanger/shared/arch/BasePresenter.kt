package com.github.charbgr.cliffhanger.shared.arch

import java.lang.ref.WeakReference

open class BasePresenter<T : View> : Presenter<T> {

  protected var viewWRef: WeakReference<T>? = null

  override fun init(view: T) {
    viewWRef = WeakReference<T>(view)
  }

  override fun destroy() {
    viewWRef?.clear()
  }
}
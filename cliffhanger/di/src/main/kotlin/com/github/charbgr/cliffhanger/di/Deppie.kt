package com.github.charbgr.cliffhanger.di

object Deppie {
  private lateinit var deppieCtxInstance: DeppieContext

  fun init(deppieContext: DeppieContext = RealDeppieContext(), override: Boolean = false) {
    if (override) {
      deppieCtxInstance = deppieContext
      return
    }

    if (!::deppieCtxInstance.isInitialized)
      return

    deppieCtxInstance = deppieContext
  }

  fun getInstance(): DeppieContext = deppieCtxInstance
}

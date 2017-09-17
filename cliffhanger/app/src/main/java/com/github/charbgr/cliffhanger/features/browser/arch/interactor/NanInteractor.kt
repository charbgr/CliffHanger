package com.github.charbgr.cliffhanger.features.browser.arch.interactor

import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange
import io.reactivex.Observable

class NanInteractor : MovieBrowserInteractor {
  override fun fetch(): Observable<PartialChange> {
    return Observable.empty()
  }

  override fun fetchMoreFrom(): Observable<PartialChange> {
    return Observable.empty()
  }

}

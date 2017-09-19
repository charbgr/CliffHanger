package com.github.charbgr.cliffhanger.features.browser.arch.interactor

import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange
import io.reactivex.Observable

class NanInteractor : MovieBrowserInteractor {
  override fun fetch(page: Int): Observable<PartialChange> {
    return Observable.empty()
  }
}

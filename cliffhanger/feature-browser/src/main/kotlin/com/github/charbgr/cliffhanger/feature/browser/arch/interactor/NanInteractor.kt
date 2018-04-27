package com.github.charbgr.cliffhanger.feature.browser.arch.interactor

import com.github.charbgr.cliffhanger.feature.browser.arch.state.PartialChange
import io.reactivex.Observable

class NanInteractor : MovieBrowserInteractor {
  override fun fetch(page: Int): Observable<PartialChange> {
    return Observable.empty()
  }
}

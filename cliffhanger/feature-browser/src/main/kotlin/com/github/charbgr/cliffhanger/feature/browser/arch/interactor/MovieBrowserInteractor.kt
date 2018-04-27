package com.github.charbgr.cliffhanger.feature.browser.arch.interactor

import com.github.charbgr.cliffhanger.feature.browser.arch.state.PartialChange
import io.reactivex.Observable

interface MovieBrowserInteractor {
  fun fetch(page: Int): Observable<PartialChange>
}

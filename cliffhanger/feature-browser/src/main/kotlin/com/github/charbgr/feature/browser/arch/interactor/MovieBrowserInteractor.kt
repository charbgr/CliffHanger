package com.github.charbgr.feature.browser.arch.interactor

import com.github.charbgr.feature.browser.arch.state.PartialChange
import io.reactivex.Observable

interface MovieBrowserInteractor {
  fun fetch(page: Int): Observable<PartialChange>
}

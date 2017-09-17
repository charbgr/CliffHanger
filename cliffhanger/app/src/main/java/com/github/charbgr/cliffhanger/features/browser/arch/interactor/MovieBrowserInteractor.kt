package com.github.charbgr.cliffhanger.features.browser.arch.interactor

import com.github.charbgr.cliffhanger.features.browser.arch.state.PartialChange
import io.reactivex.Observable

interface MovieBrowserInteractor {
  fun fetch(): Observable<PartialChange>
  fun fetchMoreFrom(): Observable<PartialChange>
}

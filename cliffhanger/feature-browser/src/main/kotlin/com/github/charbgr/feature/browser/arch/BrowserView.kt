package com.github.charbgr.feature.browser.arch

import com.github.charbgr.arch.View
import com.github.charbgr.feature.browser.arch.state.PartialChange
import io.reactivex.Observable

interface BrowserView : View {
  fun loadDataIntent(): Observable<Any>
  fun infiniteScrollIntent(): Observable<Any>

  fun render(movieBrowserViewModel: BrowserViewModel, partialChange: PartialChange)
}

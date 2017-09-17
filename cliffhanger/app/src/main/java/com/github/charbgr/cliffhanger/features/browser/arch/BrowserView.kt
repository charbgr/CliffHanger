package com.github.charbgr.cliffhanger.features.browser.arch

import com.github.charbgr.cliffhanger.shared.arch.View
import io.reactivex.Observable

interface BrowserView : View {
  fun loadDataIntent(): Observable<Any>
  fun infiniteScrollIntent(): Observable<Any>

  fun render(movieBrowserViewModel: BrowserViewModel)
}

package com.github.charbgr.cliffhanger.features.search.arch

import com.github.charbgr.arch.View
import io.reactivex.Observable

interface View : View {
  fun searchIntent(): Observable<CharSequence>
}

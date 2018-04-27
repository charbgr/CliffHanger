package com.github.charbgr.feature.home.arch

import com.github.charbgr.arch.View

interface HomeView : View {
  fun render(viewModel: HomeViewModel)
}

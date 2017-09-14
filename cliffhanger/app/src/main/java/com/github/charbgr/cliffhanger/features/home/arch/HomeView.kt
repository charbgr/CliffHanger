package com.github.charbgr.cliffhanger.features.home.arch

import com.github.charbgr.cliffhanger.shared.arch.View

interface HomeView : View {
  fun render(viewModel: HomeViewModel)
}

package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.domain.FullMovie

sealed class PartialChange {
  object Initial : PartialChange()
  object InProgress : PartialChange()
  data class Failed(val throwable: Throwable) : PartialChange()
  data class Success(val movie: FullMovie) : PartialChange()
}

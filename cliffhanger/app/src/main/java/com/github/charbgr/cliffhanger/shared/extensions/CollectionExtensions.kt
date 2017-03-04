package com.github.charbgr.cliffhanger.shared.extensions

fun <T : Any> Collection<T?>.hasAnyNullValues(block: () -> Unit = {}) {
  if (this.any { it == null }) {
    block()
  }
}
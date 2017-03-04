package com.github.charbgr.cliffhanger.shared.transformers

/**
 * This interface is responsible for transforming an object to another.
 */
interface Transformer<out T> {
  fun transform() : T
}
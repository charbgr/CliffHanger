package com.github.charbgr.arch

import io.reactivex.functions.BiFunction

/**
 * Generic StateReducer to eliminate boilerplate.
 */
abstract class BaseStateReducer<P : Any, VM : Any> {

  val reduce = BiFunction<Pair<P, VM>, P, Pair<P, VM>>
  { previousState, partialChange ->
    val toViewModel = reduceState(previousState.first, previousState.second, partialChange)
    Pair(partialChange, toViewModel)
  }

  protected abstract fun reduceState(
      previousPartialChange: P,
      previousViewModel: VM,
      partialChange: P
  ): VM
}

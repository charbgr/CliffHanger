package com.github.charbgr.cliffhanger.shared.extensions

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.reset
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions

class CollectionExtensionsKtTest {

  private interface Actions {
    fun action1()
  }

  private lateinit var actions: Actions

  @Before
  fun setUp() {
    actions = mock(Actions::class.java)
  }

  @Test
  fun test_has_null_values() {
    val fooList: List<Any?> = listOf(Object(), null)
    fooList.hasAnyNullValues { actions.action1() }

    verify(actions, times(1)).action1()
    reset(actions)
  }

  @Test
  fun test_has_not_null_values() {
    val fooList: List<Any?> = listOf(Object(), Object())
    fooList.hasAnyNullValues { actions.action1() }

    verifyZeroInteractions(actions)
  }
}
package com.github.charbgr.cliffhanger.shared.arch

import com.github.charbgr.cliffhanger.UnitTest
import org.junit.Test
import kotlin.test.assertTrue

class BasePresenterTest : UnitTest() {

  @Test
  fun test_init_view() {
    val presenter = TestBasePresenter()
    presenter.init(TestView)

    assertTrue(presenter.isInitialized())
  }

  @Test
  fun test_destroy_view() {
    val presenter = TestBasePresenter()
    presenter.init(TestView)
    presenter.destroy()

    assertTrue(presenter.isDestroyed())
  }

  private object TestView : View
  private class TestBasePresenter : BasePresenter<TestView>() {
    fun isInitialized() = viewWRef.get() != null
    fun isDestroyed() = viewWRef.get() == null
  }
}

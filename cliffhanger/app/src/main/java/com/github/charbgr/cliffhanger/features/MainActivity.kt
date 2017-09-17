package com.github.charbgr.cliffhanger.features

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.R.layout
import com.github.charbgr.cliffhanger.features.home.NavigateToHome
import com.github.charbgr.cliffhanger.shared.views.Navigatable
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

  private var container: FrameLayout by Delegates.notNull()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_main)

    container = findViewById(R.id.controller_container) as FrameLayout

    NavigateToHome(this).execute()
  }


  override fun onBackPressed() {
    val topController = container.getChildAt(container.childCount - 1)
    if (topController is Navigatable) {
      val consumed = topController.onBackPressed()
      if (consumed) {
        // do nothing if consumed, back is handled by the Navigatable
        return
      }
    }

    // if there is one controller, let activity handle back press.
    if (container.childCount == 1) {
      super.onBackPressed()
    } else {
      container.removeView(topController)
    }
  }

}

package com.github.charbgr.cliffhanger.features

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.feature.home.NavigateToHome
import com.github.charbgr.cliffhanger.shared.views.BackInterceptor
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

  private var container: FrameLayout by Delegates.notNull()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    container = findViewById(R.id.controller_container)

    NavigateToHome(this).execute()
  }


  override fun onBackPressed() {
    val topController = container.getChildAt(container.childCount - 1)
    if (topController is BackInterceptor) {
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

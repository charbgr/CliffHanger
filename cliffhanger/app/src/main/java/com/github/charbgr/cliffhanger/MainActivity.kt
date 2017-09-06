package com.github.charbgr.cliffhanger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.charbgr.cliffhanger.home.HomeController
import kotlinx.android.synthetic.main.activity_main.controller_container

class MainActivity : AppCompatActivity() {



  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val home = HomeController.inflateWith(layoutInflater)
    controller_container.addView(home)
  }

}

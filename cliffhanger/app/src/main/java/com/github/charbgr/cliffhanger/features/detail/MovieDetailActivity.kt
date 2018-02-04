package com.github.charbgr.cliffhanger.features.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.charbgr.cliffhanger.R

class MovieDetailActivity : AppCompatActivity() {

  companion object Contract {
    val MOVIE_ID_EXTRA = "movie:detail:id"
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_detail)
  }

}

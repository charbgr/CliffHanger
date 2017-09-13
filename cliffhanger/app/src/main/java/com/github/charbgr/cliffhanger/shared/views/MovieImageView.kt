package com.github.charbgr.cliffhanger.shared.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.network.tmdb.TmdbHelper

class MovieImageView : ImageView {
  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
      context, attrs, defStyleAttr, defStyleRes)


  private fun loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
  }

  fun bindImage(movie: Movie) {
    loadImage(TmdbHelper.findBestQualityBackdrop(movie))
  }

  fun clear() {
    Glide.clear(this)
  }

}

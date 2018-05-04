package com.github.charbgr.cliffhanger.shared.views.imageview

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import com.bumptech.glide.Glide

open class BaseImageView : ImageView {
  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
      context, attrs, defStyleAttr, defStyleRes)

  protected fun loadImage(url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
  }

  fun clear() {
    Glide.clear(this)
  }
}

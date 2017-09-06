package com.github.charbgr.cliffhanger.features.search

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import com.github.charbgr.cliffhanger.R

class SearchController : FrameLayout {

  companion object {
    fun inflateWith(inflater: LayoutInflater, parent: ViewGroup,
        attachToRoot: Boolean): SearchController {
      return inflater.inflate(R.layout.controller_search, null, false) as SearchController
    }
  }

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
      context, attrs, defStyleAttr, defStyleRes)

  override fun onFinishInflate() {
    super.onFinishInflate()
  }
}
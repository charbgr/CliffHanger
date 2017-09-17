package com.github.charbgr.cliffhanger.features.movie_browser

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.shared.views.Navigatable
import timber.log.Timber
import kotlin.properties.Delegates

class MovieBrowserController : RelativeLayout, Navigatable {

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
      context, attrs, defStyleAttr, defStyleRes)

  companion object {
    fun inflateWith(movieCategory: MovieCategory, inflater: LayoutInflater, parent: ViewGroup? = null, attachToRoot: Boolean = false): MovieBrowserController {
      val controller =  inflater.inflate(R.layout.controller_movie_browser, parent, attachToRoot) as MovieBrowserController
      controller.movieCategory = movieCategory
      return controller
    }
  }

  private var movieCategory: MovieCategory by Delegates.notNull()

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    Timber.d("MovieCategory : $movieCategory")
  }

  override fun onBackPressed(): Boolean = false
}

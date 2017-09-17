package com.github.charbgr.cliffhanger.features.home

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.arch.HomePresenter
import com.github.charbgr.cliffhanger.features.home.arch.HomeUiBinder
import com.github.charbgr.cliffhanger.features.home.arch.HomeView
import com.github.charbgr.cliffhanger.features.home.arch.HomeViewModel
import kotlin.properties.Delegates

class HomeController : ConstraintLayout, HomeView {

  companion object {
    fun inflateWith(inflater: LayoutInflater, parent: ViewGroup? = null,
        attachToRoot: Boolean = false): HomeController {
      return inflater.inflate(R.layout.controller_home, parent, attachToRoot) as HomeController
    }
  }

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  var movieList: RecyclerView by Delegates.notNull()
    private set

  private val presenter: HomePresenter by lazy {
    HomePresenter()
  }

  private val uiBinder: HomeUiBinder by lazy {
    HomeUiBinder(this)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    if (isInEditMode) return
    findViews()

    uiBinder.onFinishInflate()
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    presenter.init(this)
    presenter.bindIntents()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.destroy()
  }

  private fun findViews() {
    movieList = findViewById(R.id.home_movie_list)
  }

  override fun render(viewModel: HomeViewModel) = uiBinder.render(viewModel)
}

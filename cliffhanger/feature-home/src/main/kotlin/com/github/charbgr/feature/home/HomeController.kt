package com.github.charbgr.feature.home

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import charbgr.github.com.feature_home.R
import com.github.charbgr.cliffhanger.features.home.di.HomeComponent
import com.github.charbgr.feature.home.arch.HomePresenter
import com.github.charbgr.feature.home.arch.HomeUiBinder
import com.github.charbgr.feature.home.arch.HomeView
import com.github.charbgr.feature.home.arch.HomeViewModel
import javax.inject.Inject
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


  private val component: HomeComponent by Delegates.notNull()

  lateinit var search: TextView
    private set

  lateinit var movieList: RecyclerView
    private set


  @Inject
  lateinit var uiBinder: HomeUiBinder

  @Inject
  lateinit var presenter: HomePresenter

  override fun onFinishInflate() {
    super.onFinishInflate()
    if (isInEditMode) return
    findViews()
    component.inject(this)
    uiBinder.onFinishInflate()
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    if (isInEditMode) return
    presenter.init(this)
    presenter.bindIntents()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.destroy()
  }

  private fun findViews() {
    search = findViewById(R.id.home_search)
    movieList = findViewById(R.id.home_movie_list)
  }

  override fun render(viewModel: HomeViewModel) = uiBinder.render(viewModel)
}

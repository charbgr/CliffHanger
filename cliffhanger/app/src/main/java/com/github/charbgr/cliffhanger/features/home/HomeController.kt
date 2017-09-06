package com.github.charbgr.cliffhanger.features.home

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.arch.HomePresenter
import com.github.charbgr.cliffhanger.features.home.arch.HomeUiBinder
import com.github.charbgr.cliffhanger.features.home.arch.HomeView
import com.github.charbgr.cliffhanger.features.home.arch.HomeViewModel
import io.reactivex.Observable

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

  private val presenter: HomePresenter by lazy {
    HomePresenter()
  }

  private val uiBinder: HomeUiBinder by lazy {
    HomeUiBinder(this)
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    uiBinder.onFinishInflate()
    presenter.init(this)
    presenter.bindIntents()
  }

  override fun topRatedClickIntent(): Observable<Boolean> = uiBinder.topRatedClickIntent()
  override fun nowPlayingClickIntent(): Observable<Boolean> = uiBinder.nowPlayingClickIntent()
  override fun watchlistClickIntent(): Observable<Boolean> = uiBinder.watchlistClickIntent()
  override fun popularClickIntent(): Observable<Boolean> = uiBinder.popularClickIntent()
  override fun upcomingClickIntent(): Observable<Boolean> = uiBinder.upcomingClickIntent()
  override fun render(viewModel: HomeViewModel) = uiBinder.render(viewModel)
}
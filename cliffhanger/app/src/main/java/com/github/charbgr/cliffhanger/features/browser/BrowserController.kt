package com.github.charbgr.cliffhanger.features.browser

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.domain.MovieCategory
import com.github.charbgr.cliffhanger.features.browser.arch.BrowserPresenter
import com.github.charbgr.cliffhanger.features.browser.arch.BrowserView
import com.github.charbgr.cliffhanger.features.browser.arch.BrowserViewModel
import com.github.charbgr.cliffhanger.features.browser.arch.UiBinder
import com.github.charbgr.cliffhanger.shared.views.BackInterceptor
import io.reactivex.Observable
import kotlin.properties.Delegates

class BrowserController : RelativeLayout, BrowserView, BackInterceptor {

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
      context, attrs, defStyleAttr, defStyleRes)

  companion object {
    fun inflateWith(movieCategory: MovieCategory, inflater: LayoutInflater,
        parent: ViewGroup? = null, attachToRoot: Boolean = false): BrowserController {
      val controller = inflater.inflate(R.layout.controller_movie_browser, parent,
          attachToRoot) as BrowserController
      controller.presenter = BrowserPresenter(movieCategory)
      return controller
    }
  }

  private var screenTitle: TextView by Delegates.notNull()
    private set

  var movieList: RecyclerView by Delegates.notNull()
    private set

  private lateinit var presenter: BrowserPresenter
    private set

  private val uiBinder: UiBinder = UiBinder(this)

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
    uiBinder.onAttachedToWindow()
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    presenter.destroy()
  }

  override fun onBackPressed(): Boolean = false

  fun viewModel(): BrowserViewModel = presenter.viewModel

  fun setScreenTitle(title: CharSequence) {
    screenTitle.text = title
  }

  private fun findViews() {
    screenTitle = findViewById(R.id.screen_title)
    movieList = findViewById(R.id.movie_browser_list)
  }

  override fun loadDataIntent(): Observable<Any> = uiBinder.loadDataIntent()
  override fun infiniteScrollIntent(): Observable<Any> = uiBinder.infiniteScrollIntent()
  override fun render(movieBrowserViewModel: BrowserViewModel) = uiBinder.render(
      movieBrowserViewModel)
}

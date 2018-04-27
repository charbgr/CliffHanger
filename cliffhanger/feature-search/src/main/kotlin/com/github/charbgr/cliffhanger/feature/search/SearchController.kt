package com.github.charbgr.cliffhanger.feature.search

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.ProgressBar
import com.github.charbgr.cliffhanger.feature.search.arch.Presenter
import com.github.charbgr.cliffhanger.feature.search.arch.UiBinder
import com.github.charbgr.cliffhanger.feature.search.arch.View
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class SearchController : ConstraintLayout, View {

  companion object {
    fun inflateWith(inflater: LayoutInflater): SearchController {
      return inflater.inflate(R.layout.controller_search, null, false) as SearchController
    }
  }

  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)


  lateinit var search: EditText
    private set

  lateinit var movieList: RecyclerView
    private set

  lateinit var progressBar: ProgressBar
    private set

  @Inject
  internal lateinit var uiBinder: UiBinder

  @Inject
  internal lateinit var presenter: Presenter

  private val disposable: CompositeDisposable by lazy {
    CompositeDisposable()
  }

  override fun onFinishInflate() {
    super.onFinishInflate()
    if (isInEditMode) return
    findViews()
    uiBinder = UiBinder(this)
    uiBinder.onFinishInflate()
  }

  override fun onAttachedToWindow() {
    super.onAttachedToWindow()
    if (isInEditMode) return


    presenter = Presenter()
    presenter.init(this)
    presenter.bindIntents()
    presenter
        .renders()
        .subscribe { uiBinder.bind(it) }
        .addTo(disposable)
  }

  override fun onDetachedFromWindow() {
    super.onDetachedFromWindow()
    disposable.clear()
    presenter.destroy()
  }

  private fun findViews() {
    search = findViewById(R.id.controller_search_search)
    movieList = findViewById(R.id.controller_search_movie_list)
    progressBar = findViewById(R.id.controler_search_progressBar)
  }

  override fun searchIntent(): Observable<CharSequence> = uiBinder.searchIntent()
}

package com.github.charbgr.cliffhanger.features.detail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ProgressBar
import android.widget.TextView
import com.github.charbgr.cliffhanger.features.detail.arch.Presenter
import com.github.charbgr.cliffhanger.features.detail.arch.UiBinder
import com.github.charbgr.cliffhanger.features.detail.arch.View
import com.github.charbgr.cliffhanger.features.detail.di.MovieDetailComponent
import com.github.charbgr.cliffhanger.shared.views.imageview.MovieImageView
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject
import kotlin.properties.Delegates

class MovieDetailActivity : AppCompatActivity(), View {

  companion object Contract {
    const val MOVIE_ID_EXTRA = "movie:detail:id"
  }

  private val component: MovieDetailComponent by Delegates.notNull()

  @Inject
  internal lateinit var uiBinder: UiBinder

  @Inject
  internal lateinit var presenter: Presenter

  private val disposable: CompositeDisposable = CompositeDisposable()

  lateinit var backdrop: MovieImageView
  lateinit var title: TextView
  lateinit var poster: MovieImageView
  lateinit var progressBar: ProgressBar
  lateinit var overview: TextView
  lateinit var tagline: TextView
  lateinit var directedBy: TextView
  lateinit var director: TextView
  lateinit var chronology: TextView
  lateinit var duration: TextView

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_movie_detail)
    findViews()
    component.inject(this)
    setUpPresenter()
    uiBinder.onCreateView()
  }

  override fun onDestroy() {
    presenter.destroy()
    disposable.clear()
    super.onDestroy()
  }

  private fun setUpPresenter() {
    presenter.bindIntents()
    presenter
        .renders()
        .subscribe { uiBinder.render(it) }
        .addTo(disposable)
  }

  private fun findViews() {
    backdrop = findViewById(R.id.movie_detail_backdrop) as MovieImageView
    title = findViewById(R.id.movie_detail_title) as TextView
    poster = findViewById(R.id.movie_detail_poster) as MovieImageView
    overview = findViewById(R.id.movie_detail_overview) as TextView
    tagline = findViewById(R.id.movie_detail_tagline) as TextView
    directedBy = findViewById(R.id.movie_detail_directed_by) as TextView
    director = findViewById(R.id.movie_detail_director) as TextView
    chronology = findViewById(R.id.movie_detail_chronology) as TextView
    duration = findViewById(R.id.movie_detail_duration) as TextView
    progressBar = findViewById(R.id.movie_detail_progressbar) as ProgressBar
  }

  override fun fetchMovieIntent(): Observable<Int> = uiBinder.fetchMovieIntent()
}

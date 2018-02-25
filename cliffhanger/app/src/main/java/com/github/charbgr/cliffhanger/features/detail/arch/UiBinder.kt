package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.api_tmdb.TmdbHelper
import com.github.charbgr.cliffhanger.domain.FullMovie
import com.github.charbgr.cliffhanger.features.detail.MovieDetailActivity
import com.github.charbgr.cliffhanger.features.error.NavigateToError
import com.github.charbgr.cliffhanger.shared.extensions.visibleOrGone
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

internal open class UiBinder(
    private val movieDetailActivity: MovieDetailActivity
) : View {

  private val movieLoadIntent: PublishSubject<Int> = PublishSubject.create()

  fun onCreateView() {
    loadMovie()
  }

  fun render(render: Pair<PartialChange, ViewModel>) {
    val viewModel = render.second

    movieDetailActivity.progressBar.visibleOrGone(viewModel.showLoader)

    if (viewModel.showError) {
      NavigateToError(movieDetailActivity)
          .closeCurrentActivity()
          .execute()
    }

    if (viewModel.showMovie) {
      bindMovie(viewModel.movie!!)
    }
  }

  private fun bindMovie(movie: FullMovie) {
    movieDetailActivity.title.text = movie.title
    bindImages(movie)
    bindDirector(movie)
    bindSecondaryInfo(movie)
    bindTagline(movie)
    movieDetailActivity.overview.text = movie.overview
  }

  private fun bindImages(movie: FullMovie) {
    movieDetailActivity.poster.bindPoster(TmdbHelper.bestPoster(movie.posterPath))
    movieDetailActivity.backdrop.bindBackdrop(TmdbHelper.bestBackdrop(movie.backdropPath))
  }

  private fun bindDirector(movie: FullMovie) {
    val director = movie.director

    movieDetailActivity.directedBy.visibleOrGone(director != null)
    movieDetailActivity.director.visibleOrGone(director != null)
    movieDetailActivity.director.text = director?.name
  }

  private fun bindSecondaryInfo(movie: FullMovie) {
    movieDetailActivity.duration.text = movieDetailActivity.getString(R.string.movie_duration_mins,
        movie.duration)
    movieDetailActivity.chronology.text = movie.chronology
  }

  private fun bindTagline(movie: FullMovie) {
    movieDetailActivity.tagline.visibleOrGone(!movie.tagline.isNullOrBlank())
    movieDetailActivity.tagline.text = movie.tagline
  }


  private fun loadMovie() {
    val movieId = movieDetailActivity.intent.getIntExtra(MovieDetailActivity.MOVIE_ID_EXTRA, -1)
    movieLoadIntent.onNext(movieId)
  }

  override fun fetchMovieIntent(): Observable<Int> = movieLoadIntent.hide()

}

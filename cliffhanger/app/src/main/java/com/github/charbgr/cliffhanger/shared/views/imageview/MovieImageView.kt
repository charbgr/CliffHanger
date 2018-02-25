package com.github.charbgr.cliffhanger.shared.views.imageview

import android.content.Context
import android.util.AttributeSet
import com.github.charbgr.cliffhanger.api_tmdb.TmdbHelper
import com.github.charbgr.cliffhanger.domain.FullMovie
import com.github.charbgr.cliffhanger.domain.MiniMovie

class MovieImageView : BaseImageView {
  constructor(context: Context?) : super(context)
  constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr)

  constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(
      context, attrs, defStyleAttr, defStyleRes)


  fun bindBackdrop(movie: MiniMovie) {
    loadImage(TmdbHelper.findBestQualityBackdrop(movie.backdropPath))
  }

  fun bindPoster(movie: MiniMovie) {
    loadImage(TmdbHelper.findBestQualityPoster(movie.posterPath))
  }

  fun bindBackdrop(movie: FullMovie) {
    loadImage(TmdbHelper.findBestQualityBackdrop(movie.backdropPath))
  }

  fun bindPoster(movie: FullMovie) {
    loadImage(TmdbHelper.findBestQualityPoster(movie.posterPath))
  }

}

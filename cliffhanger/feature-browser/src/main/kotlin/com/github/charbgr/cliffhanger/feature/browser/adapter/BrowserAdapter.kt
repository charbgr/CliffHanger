package com.github.charbgr.cliffhanger.feature.browser.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.charbgr.baseadapter.BaseRvAdapter
import com.github.charbgr.cliffhanger.api_tmdb.TmdbHelper
import com.github.charbgr.cliffhanger.feature.browser.R
import com.github.charbgr.cliffhanger.shared.extensions.render
import com.github.charbgr.cliffhanger.shared.views.imageview.MovieImageView

class BrowserAdapter : BaseRvAdapter<BrowserAdapterItem>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    val itemView = parent.render(R.layout.item_browser_movie)
    return MovieViewHolder(itemView)
  }


  inner class MovieViewHolder(itemView: View): BaseViewHolder(itemView) {

    private var movieTv: TextView = itemView.findViewById(R.id.item_movie_name)
    private var moviePosterIv: MovieImageView = itemView.findViewById(R.id.item_movie_poster)

    override fun bind(item: BrowserAdapterItem, position: Int) {
      item as MovieAdapterItem
      movieTv.text = item.movie.title
      moviePosterIv.bindBackdrop(TmdbHelper.bestBackdrop(item.movie.backdropPath))
    }

    override fun clear() {

    }

  }
}

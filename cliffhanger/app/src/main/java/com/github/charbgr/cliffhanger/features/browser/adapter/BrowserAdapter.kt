package com.github.charbgr.cliffhanger.features.browser.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.shared.adapter.BaseRvAdapter
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
      moviePosterIv.bindImage(item.movie)
    }

    override fun clear() {

    }

  }
}

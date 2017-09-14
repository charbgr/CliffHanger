package com.github.charbgr.cliffhanger.features.home.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.features.home.adapter.MovieGroupItem.ViewTypes
import com.github.charbgr.cliffhanger.shared.adapter.BaseRvAdapter
import com.github.charbgr.cliffhanger.shared.adapter.movies.MovieAdapter
import com.github.charbgr.cliffhanger.shared.extensions.render
import com.github.charbgr.cliffhanger.shared.views.recyclerview.addSpacing
import kotlinx.android.synthetic.main.item_movies_carousel.view.item_movie_carousel_list
import kotlinx.android.synthetic.main.item_section.view.item_section_title

class MovieGroupAdapter(
    private val sharedRvPool: RecyclerView.RecycledViewPool) : BaseRvAdapter<MovieGroupItem>() {

  override fun onCreateViewHolder(parent: ViewGroup,
      viewType: Int): BaseViewHolder {
    return when (viewType) {
      ViewTypes.MOVIES_CAROUSEL -> {
        val itemView = parent.render(R.layout.item_movies_carousel)
        MoviesCarouselViewHolder(itemView)
      }

      ViewTypes.SECTION -> {
        val itemView = parent.render(R.layout.item_section)
        SectionViewHolder(itemView)
      }
      else -> {
        throwUnsupportedViewType(viewType)
      }
    }
  }

  inner class MoviesCarouselViewHolder(itemView: View) : BaseViewHolder(itemView) {

    val movieAdapter: MovieAdapter by lazy {
      MovieAdapter()
    }

    override fun bind(item: MovieGroupItem, position: Int) {
      item as MovieCarouselItem
      itemView.item_movie_carousel_list.apply {
        recycledViewPool = sharedRvPool
        val gridColumns = context.resources.getInteger(R.integer.home_grid_columns)
        val lm = GridLayoutManager(context, gridColumns, GridLayoutManager.HORIZONTAL, false)
        lm.spanSizeLookup = object : SpanSizeLookup() {
          override fun getSpanSize(movieItemPosition: Int): Int {
            return movieAdapter.getItemAt(movieItemPosition)?.getSpanSize(movieItemPosition) ?: gridColumns
          }
        }
        layoutManager = lm
        addSpacing(1f)
        adapter = movieAdapter
        movieAdapter.setItems(item.movieAdapterItems)
      }

    }

    override fun clear() {
    }
  }

  inner class SectionViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun bind(item: MovieGroupItem, position: Int) {
      item as SectionHeaderItem
      itemView.item_section_title.text = item.title
    }

    override fun clear() {
    }
  }
}

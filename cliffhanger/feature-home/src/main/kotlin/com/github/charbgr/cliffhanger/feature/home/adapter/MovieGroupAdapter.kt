package com.github.charbgr.cliffhanger.feature.home.adapter

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.GridLayoutManager.SpanSizeLookup
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ItemDecoration
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import charbgr.github.com.feature_home.R
import com.github.charbgr.baseadapter.BaseRvAdapter
import com.github.charbgr.cliffhanger.feature.browser.NavigateToBrowser
import com.github.charbgr.cliffhanger.feature.home.adapter.MovieGroupItem.ViewTypes
import com.github.charbgr.cliffhanger.shared.extensions.addSpacing
import com.github.charbgr.cliffhanger.shared.extensions.render
import com.github.charbgr.cliffhanger.shared.movies.MovieAdapter

class MovieGroupAdapter(
  private val sharedRvPool: RecyclerView.RecycledViewPool
) : BaseRvAdapter<MovieGroupItem>() {

  override fun onCreateViewHolder(
    parent: ViewGroup,
    viewType: Int
  ): BaseViewHolder {
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

    private var carouselList: RecyclerView = itemView.findViewById(R.id.item_movie_carousel_list)

    private val movieAdapter: MovieAdapter by lazy {
      MovieAdapter()
    }

    private var spaceItemDecorator: ItemDecoration? = null

    override fun bind(item: MovieGroupItem, position: Int) {
      item as MovieCarouselItem
      carouselList.apply {
        recycledViewPool = sharedRvPool
        val gridColumns = context.resources.getInteger(R.integer.home_grid_columns)
        val lm = GridLayoutManager(context, gridColumns, GridLayoutManager.HORIZONTAL, false)
        lm.spanSizeLookup = object : SpanSizeLookup() {
          override fun getSpanSize(movieItemPosition: Int): Int {
            return movieAdapter.getItemAt(movieItemPosition)?.getSpanSize(movieItemPosition)
                ?: gridColumns
          }
        }
        layoutManager = lm
        spaceItemDecorator = addSpacing(1f)
        adapter = movieAdapter
        movieAdapter.setItems(item.movieAdapterItems)
      }
    }

    override fun clear() {
      carouselList.removeItemDecoration(spaceItemDecorator)
    }
  }

  inner class SectionViewHolder(itemView: View) : BaseViewHolder(itemView) {

    private var sectionTitle: TextView = itemView.findViewById(R.id.item_section_title)

    override fun bind(item: MovieGroupItem, position: Int) {
      item as SectionHeaderItem
      sectionTitle.text = item.title
      itemView.setOnClickListener {
        NavigateToBrowser(itemView.context, item.movieCategory).execute()
      }
    }

    override fun clear() {
    }
  }
}

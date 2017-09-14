package com.github.charbgr.cliffhanger.features.home.movies

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.shared.extensions.render
import kotlinx.android.synthetic.main.item_movie.view.item_movie_layout
import kotlinx.android.synthetic.main.item_movie.view.item_movie_name
import kotlinx.android.synthetic.main.item_movie.view.item_movie_poster
import kotlinx.android.synthetic.main.item_section.view.item_section_title

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.BaseViewHolder>() {

  private val itemList: MutableList<MovieAdapterItem> = mutableListOf()

  override fun getItemCount(): Int = itemList.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    return when (viewType) {
      MovieAdapterItem.ViewTypes.SECTION -> {
        val itemView = parent.render(R.layout.item_section)
        SectionViewHolder(itemView)
      }
      MovieAdapterItem.ViewTypes.MOVIE -> {
        val itemView = parent.render(R.layout.item_movie)
        MovieViewHolder(itemView)
      }
      else -> {
        throw IllegalStateException("Unsupported viewtype on MovieAdapter $viewType")
      }
    }
  }

  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    val item = getItemAt(position)
    if (item != null) {
      holder.bind(item, position)
    } else {
      holder.clear()
    }
  }

  override fun onViewRecycled(holder: BaseViewHolder?) {
    holder?.clear()
    super.onViewRecycled(holder)
  }

  override fun getItemViewType(position: Int): Int {
    return getItemAt(position)?.getItemViewType() ?: 0
  }

  fun setItems(items: List<MovieAdapterItem>) {
    this.itemList.clear()
    this.itemList.addAll(items)
    notifyDataSetChanged()
  }

  fun getItemAt(position: Int): MovieAdapterItem? = itemList.getOrNull(position)


  inner abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: MovieAdapterItem, position: Int)
    abstract fun clear()
  }

  inner class SectionViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun bind(item: MovieAdapterItem, position: Int) {
      item as SectionHeaderItem
      itemView.item_section_title.text = item.title
    }

    override fun clear() {
    }
  }

  inner class MovieViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun bind(item: MovieAdapterItem, position: Int) {
      item as MovieItem
      applyConstraints(item, position)
      itemView.item_movie_name.text = item.movie.title
      itemView.item_movie_poster.bindImage(item.movie)
    }

    override fun clear() {
      itemView.item_movie_poster.clear()
    }

    private fun applyConstraints(movie: MovieItem, position: Int) {
      val constraintLayout: ConstraintLayout = itemView.item_movie_layout
      ConstraintSet().apply {
        clone(constraintLayout)
        setDimensionRatio(itemView.item_movie_poster.id, movie.getAspectRatio(position))
        applyTo(constraintLayout)
      }
    }
  }
}

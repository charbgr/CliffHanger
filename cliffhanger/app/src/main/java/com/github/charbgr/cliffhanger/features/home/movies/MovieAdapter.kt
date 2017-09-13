package com.github.charbgr.cliffhanger.features.home.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.shared.extensions.render
import kotlinx.android.synthetic.main.item_movie.view.item_movie_name
import kotlinx.android.synthetic.main.item_movie.view.item_movie_poster

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.BaseViewHolder>() {

  private val itemList: MutableList<MovieAdapterItem> = mutableListOf()

  override fun getItemCount(): Int = itemList.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    return when (viewType) {
      R.layout.item_movie -> {
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
      holder.bind(item)
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
    abstract fun bind(item: MovieAdapterItem)
    abstract fun clear()
  }

  inner class MovieViewHolder(itemView: View) : BaseViewHolder(itemView) {
    override fun bind(item: MovieAdapterItem) {
      item as MovieItem
      bindText(item.movie)
      bindImage(item.movie)
    }

    override fun clear() {
      itemView.item_movie_poster.clear()
    }

    private fun bindText(movie: Movie) {
      itemView.item_movie_name.text = movie.title
    }

    private fun bindImage(movie: Movie) {
     itemView.item_movie_poster.bindImage(movie)
    }
  }
}

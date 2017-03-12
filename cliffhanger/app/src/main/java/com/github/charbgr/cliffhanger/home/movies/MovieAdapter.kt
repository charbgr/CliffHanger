package com.github.charbgr.cliffhanger.home.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.github.charbgr.cliffhanger.Movie
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.shared.extensions.render

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
  override fun getItemCount(): Int = 6

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView = parent.render(R.layout.item_movie)
    return ViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val view = holder.itemView.findViewById(R.id.item_movie_poster) as ImageView
    if (position % 2 == 0) {
      view.setImageResource(R.drawable.internship)
    } else {
      view.setImageResource(R.drawable.moonlight)
    }
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie) {

    }
  }
}

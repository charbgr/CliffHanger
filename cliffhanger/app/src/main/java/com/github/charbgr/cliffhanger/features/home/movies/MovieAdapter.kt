package com.github.charbgr.cliffhanger.features.home.movies

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.github.charbgr.cliffhanger.domain.Movie
import com.github.charbgr.cliffhanger.R
import com.github.charbgr.cliffhanger.shared.extensions.render
import com.github.charbgr.cliffhanger.network.tmdb.TmdbHelper
import kotlinx.android.synthetic.main.item_movie.view.item_movie_name
import kotlinx.android.synthetic.main.item_movie.view.item_movie_poster

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

  private val movieList: MutableList<Movie> = mutableListOf()

  override fun getItemCount(): Int = movieList.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val itemView = parent.render(R.layout.item_movie)
    return ViewHolder(itemView)
  }

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    val movie = movieList[holder.adapterPosition]

    holder.bind(movie)
  }

  fun setMovies(movieList: List<Movie>) {
    this.movieList.clear()
    this.movieList.addAll(movieList)
    notifyDataSetChanged()
  }

  fun addMovies(movieList: List<Movie>) {
    val previousSize = this.movieList.size
    this.movieList.addAll(movieList)
    notifyItemRangeInserted(previousSize, movieList.size)
  }

  inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(movie: Movie) {
      bindText(movie)
      bindImage(movie)
    }

    private fun bindText(movie: Movie) {
      itemView.item_movie_name.text = movie.title
    }

    private fun bindImage(movie: Movie) {
      Glide.with(itemView.context)
          .load(TmdbHelper.findBestQualityBackdrop(movie))
          .into(itemView.item_movie_poster)
    }

  }
}

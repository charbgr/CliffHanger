package com.github.charbgr.cliffhanger.shared.repository

import com.github.charbgr.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.api_tmdb.TmdbAPI

class RealMovieRepositoryModule(schedulerProvider: SchedulerProvider) : MovieRepositoryModule {

  private val tmdbAPI = TmdbAPI(schedulerProvider.io())

  override val movieRepository: MovieRepository = object : MovieRepository {

    override fun getMovie(movieId: Int) = tmdbAPI.movieDAO.getMovie(movieId)

    override fun searchMovies(query: CharSequence, page: Int) =
      tmdbAPI.movieDAO.searchMovie(query.toString(), page)

    override fun fetchPopularMovies(page: Int) = tmdbAPI.movieDAO.popularMovies(page)
    override fun fetchTopRatedMovies(page: Int) = tmdbAPI.movieDAO.topRatedMovies(page)
    override fun fetchUpcomingMovies(page: Int) = tmdbAPI.movieDAO.upcomingMovies(page)
    override fun fetchNowPlayingMovies(page: Int) = tmdbAPI.movieDAO.nowPlayingMovie(page)
  }
}

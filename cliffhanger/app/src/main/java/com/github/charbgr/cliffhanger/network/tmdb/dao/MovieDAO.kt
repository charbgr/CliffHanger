package com.github.charbgr.cliffhanger.network.tmdb.dao

import android.graphics.Movie
import com.github.charbgr.cliffhanger.network.tmdb.Routes
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


interface MovieDAO {

  @GET(Routes.MOVIE_ROUTE + "{movieId}")
  fun getMovie(@Path("movieId") movieId: Int): Observable<Movie>

  @GET(Routes.MOVIE_ROUTE + "latest")
  fun latestMovies(): Observable<MovieResults>

  @GET(Routes.MOVIE_ROUTE + "popular")
  fun popularMovies(): Observable<MovieResults>

  @GET(Routes.MOVIE_ROUTE + "top_rated")
  fun topRatedMovies(): Observable<MovieResults>

  @GET(Routes.MOVIE_ROUTE + "upcoming")
  fun upcomingMovies(): Observable<MovieResults>

  @GET(Routes.MOVIE_ROUTE + "now_playing")
  fun nowPlayingMovie(): Observable<MovieResults>
}
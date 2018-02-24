package com.github.charbgr.cliffhanger.network.tmdb.dao

import com.github.charbgr.cliffhanger.network.tmdb.Routes
import com.github.charbgr.cliffhanger.network.tmdb.entity.FullMovieEntity
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieDAO {

  @GET(Routes.MOVIE_ROUTE + "{movieId}?append_to_response=credits")
  fun getMovie(@Path("movieId") movieId: Int): Single<FullMovieEntity>

  @GET(Routes.MOVIE_ROUTE + "latest")
  fun latestMovies(): Observable<MovieResults>

  @GET(Routes.MOVIE_ROUTE + "popular")
  fun popularMovies(@Query("page") page: Int): Observable<MovieResults>

  @GET(Routes.MOVIE_ROUTE + "top_rated")
  fun topRatedMovies(@Query("page") page: Int): Observable<MovieResults>

  @GET(Routes.MOVIE_ROUTE + "upcoming")
  fun upcomingMovies(@Query("page") page: Int): Observable<MovieResults>

  @GET(Routes.MOVIE_ROUTE + "now_playing")
  fun nowPlayingMovie(@Query("page") page: Int): Observable<MovieResults>
}

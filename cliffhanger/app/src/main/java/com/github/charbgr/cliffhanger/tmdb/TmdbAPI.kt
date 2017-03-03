package com.github.charbgr.cliffhanger.tmdb

import com.github.charbgr.cliffhanger.BuildConfig
import com.github.charbgr.cliffhanger.tmdb.dao.MovieDAO
import com.github.charbgr.cliffhanger.tmdb.dao.SearchDAO
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class TmdbAPI private constructor(scheduler: Scheduler) {

  companion object {
    fun create(scheduler: Scheduler = Schedulers.io()): TmdbAPI {
      return TmdbAPI(scheduler)
    }
  }

  val retrofit: Retrofit by lazy {

    val okHttpClient: OkHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(apiKeyInterceptor())
        .build()

    Retrofit.Builder()
        .baseUrl(Routes.BASE_BACKEND_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
  }

  val movieDAO: MovieDAO by lazy {
    retrofit.create(MovieDAO::class.java)
  }

  val searchDAO: SearchDAO by lazy {
    retrofit.create(SearchDAO::class.java)
  }

  private fun apiKeyInterceptor(): Interceptor = Interceptor {
    val request = it.request()
    val requestBuilder = request.newBuilder()
    val urlHttpBuilder = request.url().newBuilder()
    urlHttpBuilder.addQueryParameter("api_key", BuildConfig.TMDB_API_KEY)

    val newRequest = requestBuilder.url(urlHttpBuilder.build()).build()

    it.proceed(newRequest)
  }
}

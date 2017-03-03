package com.github.charbgr.cliffhanger.shared.di

import com.github.charbgr.cliffhanger.tmdb.Routes
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


class DependecyInjection private constructor() {

  companion object {
    fun create() {

    }

    fun tmdbRetrofit(scheduler: Scheduler = Schedulers.io()): Retrofit =
        Retrofit.Builder()
            .baseUrl(Routes.BASE_BACKEND_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(scheduler))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
  }


}

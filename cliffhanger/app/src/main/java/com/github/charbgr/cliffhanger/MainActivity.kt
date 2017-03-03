package com.github.charbgr.cliffhanger

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.github.charbgr.cliffhanger.home.HomeController
import com.github.charbgr.cliffhanger.tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.tmdb.dao.MovieResults
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_main.controller_container
import timber.log.Timber

class MainActivity : AppCompatActivity() {

  private var router: Router? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    initRouter(savedInstanceState)
  }

  private fun initRouter(savedInstanceState: Bundle?) {
    router = Conductor.attachRouter(this, controller_container, savedInstanceState)

    router?.let {
      if (!it.hasRootController()) {
        it.setRoot(RouterTransaction.with(HomeController()).tag("home"))
      }
    }


    TmdbAPI
        .create()
        .movieDAO
        .popularMovies()
        .subscribeWith(object : Observer<MovieResults> {
          override fun onSubscribe(d: Disposable?) {
          }

          override fun onNext(t: MovieResults?) {
            Timber.d(t.toString())
          }

          override fun onError(e: Throwable?) {
            Timber.e(e)
          }

          override fun onComplete() {
          }

        })


  }

  override fun onBackPressed() {
    if (!(router?.handleBack() ?: true)) {
      super.onBackPressed()
    }
  }
}

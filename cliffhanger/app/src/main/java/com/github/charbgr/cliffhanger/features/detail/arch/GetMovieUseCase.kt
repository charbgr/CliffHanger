package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.features.detail.arch.MovieRepository.Network
import com.github.charbgr.cliffhanger.network.tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.shared.arch.SchedulerProvider
import com.github.charbgr.cliffhanger.shared.arch.UseCase
import io.reactivex.Observable

internal class GetMovieUseCase(
    private val movieRepository: MovieRepository = Network(
        TmdbAPI.create(SchedulerProvider.Default.io()))
) : UseCase.RxObservable<PartialChange, Int>() {

  override fun build(params: Int): Observable<PartialChange> {
    return movieRepository.getMovie(params)
        .toObservable()
        .map { PartialChange.Success(it) as PartialChange }
        .onErrorReturn { PartialChange.Failed(it) }
        .startWith(PartialChange.InProgress)
        .share()
  }

}

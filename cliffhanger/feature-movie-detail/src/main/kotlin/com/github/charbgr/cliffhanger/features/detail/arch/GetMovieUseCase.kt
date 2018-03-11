package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.api_tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.api_tmdb.entity.FullMovieEntityMapper.transform
import com.github.charbgr.cliffhanger.features.detail.arch.MovieRepository.Network
import com.github.charbgr.cliffhanger.shared.arch.UseCase
import com.github.charbgr.cliffhanger.shared.extensions.AndroidSchedulerProvider
import io.reactivex.Observable

internal class GetMovieUseCase(
    private val movieRepository: MovieRepository = Network(
        TmdbAPI(AndroidSchedulerProvider.io()))
) : UseCase.RxObservable<PartialChange, Int>() {

  override fun build(params: Int): Observable<PartialChange> {
    return movieRepository.getMovie(params)
        .toObservable()
        .map { PartialChange.Success(transform(it)) as PartialChange }
        .onErrorReturn { PartialChange.Failed(it) }
        .startWith(PartialChange.InProgress)
        .share()
  }

}

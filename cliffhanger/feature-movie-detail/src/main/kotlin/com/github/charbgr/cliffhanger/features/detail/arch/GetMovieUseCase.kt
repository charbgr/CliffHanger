package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.arch.UseCase
import com.github.charbgr.cliffhanger.api_tmdb.entity.FullMovieEntityMapper.transform
import com.github.charbgr.cliffhanger.di.Deppie
import io.reactivex.Observable

class GetMovieUseCase : UseCase.RxObservable<PartialChange, Int>() {

  private val movieRepository = Deppie.getInstance().movieRepository

  override fun build(params: Int): Observable<PartialChange> {
    return movieRepository.getMovie(params)
        .toObservable()
        .map { PartialChange.Success(transform(it)) as PartialChange }
        .onErrorReturn { PartialChange.Failed(it) }
        .startWith(PartialChange.InProgress)
        .share()
  }
}

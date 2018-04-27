package com.github.charbgr.cliffhanger.features.detail.arch

import com.github.charbgr.cliffhanger.api_tmdb.entity.FullMovieEntityMapper.transform
import com.github.charbgr.cliffhanger.shared.arch.UseCase
import com.github.charbgr.cliffhanger.shared.extensions.AndroidSchedulerProvider
import com.github.charbgr.cliffhanger.shared.repository.MovieRepository
import com.github.charbgr.cliffhanger.shared.repository.MovieRepository.Tmdb
import io.reactivex.Observable

class GetMovieUseCase(
    private val movieRepository: MovieRepository = Tmdb(AndroidSchedulerProvider)
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

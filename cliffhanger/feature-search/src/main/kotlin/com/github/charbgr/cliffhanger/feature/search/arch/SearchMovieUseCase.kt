package com.github.charbgr.cliffhanger.feature.search.arch

import com.github.charbgr.cliffhanger.api_tmdb.entity.SearchResultsEntityMapper
import com.github.charbgr.cliffhanger.feature.search.arch.PartialChange.Failed
import com.github.charbgr.cliffhanger.feature.search.arch.PartialChange.InProgress
import com.github.charbgr.cliffhanger.feature.search.arch.PartialChange.Success
import com.github.charbgr.cliffhanger.feature.search.arch.SearchMovieUseCase.Params
import com.github.charbgr.cliffhanger.shared.arch.UseCase
import com.github.charbgr.cliffhanger.shared.extensions.AndroidSchedulerProvider
import com.github.charbgr.cliffhanger.shared.repository.MovieRepository
import com.github.charbgr.cliffhanger.shared.repository.MovieRepository.Tmdb
import io.reactivex.Observable

class SearchMovieUseCase(
  private val movieRepository: MovieRepository = Tmdb(AndroidSchedulerProvider)
) : UseCase.RxObservable<PartialChange, Params>() {

  override fun build(params: Params): Observable<PartialChange> {
    return movieRepository
        .searchMovies(query = params.query, page = params.page)
        .toObservable()
        .map {
          val searchResults = SearchResultsEntityMapper.transform(it)
          val fromInfiniteScroll = params.fromInfiniteScroll
          Success(searchResults, fromInfiniteScroll) as PartialChange
        }
        .onErrorReturn { Failed(it) }
        .startWith(InProgress(
            params.fromInfiniteScroll))
        .share()
  }

  data class Params(val query: CharSequence, val page: Int) {
    val fromInfiniteScroll: Boolean
      get() = page > 1
  }
}

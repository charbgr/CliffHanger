package com.github.charbgr.cliffhanger.features.search.arch

import com.github.charbgr.cliffhanger.features.detail.arch.MovieRepository
import com.github.charbgr.cliffhanger.features.detail.arch.MovieRepository.Network
import com.github.charbgr.cliffhanger.features.search.arch.SearchMovieUseCase.Params
import com.github.charbgr.cliffhanger.network.tmdb.TmdbAPI
import com.github.charbgr.cliffhanger.network.tmdb.entity.SearchResultsEntityMapper
import com.github.charbgr.cliffhanger.shared.arch.UseCase
import com.github.charbgr.cliffhanger.shared.extensions.AndroidSchedulerProvider
import io.reactivex.Observable

internal class SearchMovieUseCase(
    private val movieRepository: MovieRepository = Network(
        TmdbAPI.create(AndroidSchedulerProvider.io()))
) : UseCase.RxObservable<PartialChange, Params>() {

  override fun build(params: Params): Observable<PartialChange> {
    return movieRepository
        .searchMovies(query = params.query, page = params.page)
        .toObservable()
        .map {
          val searchResults = SearchResultsEntityMapper.transform(it)
          val fromInfiniteScroll = params.fromInfiniteScroll
          PartialChange.Success(searchResults, fromInfiniteScroll) as PartialChange
        }
        .onErrorReturn { PartialChange.Failed(it) }
        .startWith(PartialChange.InProgress(params.fromInfiniteScroll))
        .share()
  }

  internal data class Params(val query: CharSequence, val page: Int) {
    val fromInfiniteScroll: Boolean
      get() = page > 1
  }
}

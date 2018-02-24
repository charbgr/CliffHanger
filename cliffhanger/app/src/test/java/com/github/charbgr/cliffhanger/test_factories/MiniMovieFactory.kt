package com.github.charbgr.cliffhanger.test_factories

import com.github.charbgr.cliffhanger.domain.MiniMovie
import com.github.charbgr.cliffhanger.network.tmdb.entity.MiniMovieEntity

object MiniMovieFactory {

  val FightClubEntity: MiniMovieEntity =
      MiniMovieEntity(
          id = 1,
          title = "Fight Club",
          posterPath = "http://foo.bar",
          backdropPath = "http://foo.bar/baz/qux.jpg"
      )

  val FightClub: MiniMovie = MiniMovie(
      tmdbId = 1,
      title = "Fight Club",
      posterPath = "http://foo.bar",
      backdropPath = "http://foo.bar/baz/qux.jpg"
  )

}

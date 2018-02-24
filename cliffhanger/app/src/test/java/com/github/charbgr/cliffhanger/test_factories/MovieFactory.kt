package com.github.charbgr.cliffhanger.test_factories

import com.github.charbgr.cliffhanger.domain.FullMovie
import com.github.charbgr.cliffhanger.network.tmdb.entity.CreditsEntity
import com.github.charbgr.cliffhanger.network.tmdb.entity.FullMovieEntity

object MovieFactory {

  val FightClubEntity: FullMovieEntity =
      FullMovieEntity(
          id = 1,
          title = "Fight Club",
          posterPath = "http://foo.bar",
          backdropPath = "http://foo.bar/baz/qux.jpg",
          overview = "an overview",
          tagline = "fight fight fight",
          credits = CreditsEntity(emptyList()),
          runtime = 42,
          releaseDate = "2000-02-18"
      )

  val FightClub: FullMovie = FullMovie(
      tmdbId = 1,
      title = "Fight Club",
      posterPath = "http://foo.bar",
      backdropPath = "http://foo.bar/baz/qux.jpg",
      overview = "an overview",
      tagline = "fight fight fight",
      crewMembers = emptyList(),
      duration = 42,
      releaseDate = "2000-02-18"
  )

}

package com.github.charbgr.cliffhanger.test_factories

import com.github.charbgr.cliffhanger.domain.Movie

object MovieFactory {

  val FightClub: Movie = Movie(
      tmdbId = 1,
      title = "Fight Club",
      posterPath = "http://foo.bar",
      backdropPath = "http://foo.bar/baz/qux.jpg")
}

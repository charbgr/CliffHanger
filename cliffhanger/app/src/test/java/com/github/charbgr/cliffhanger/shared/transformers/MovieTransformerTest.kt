package com.github.charbgr.cliffhanger.shared.transformers

import com.github.charbgr.cliffhanger.Movie
import com.github.charbgr.cliffhanger.shared.transformers.movie.MovieTransformer
import com.github.charbgr.cliffhanger.test_factories.MiniMovieDtoFactory
import com.github.charbgr.cliffhanger.test_factories.MovieFactory
import com.github.charbgr.cliffhanger.tmdb.dto.MiniMovieDto
import org.junit.Assert
import org.junit.Test

class MovieTransformerTest {

  @Test
  fun test_creation_of_movie_with_MiniMovieDto() {
    val miniMovieDto: MiniMovieDto = MiniMovieDtoFactory.EMPTY.copy(id = 1, title = "Fight Club")
    val movie: Movie = MovieTransformer(miniMovieDto).transform()

    Assert.assertNotNull(movie)
    Assert.assertEquals(MovieFactory.FightClub, movie)
  }

  @Test(expected = TransformationException::class)
  fun test_throwing_excpetion_on_creation_of_movie_with_MiniMovieDto() {
    val miniMovieDto: MiniMovieDto = MiniMovieDtoFactory.EMPTY
    MovieTransformer(miniMovieDto).transform()
  }

}
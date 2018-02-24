package com.github.charbgr.cliffhanger.domain

import com.github.charbgr.cliffhanger.shared.extensions.empty

data class FullMovie(
    val tmdbId: Int,
    val title: String,
    val posterPath: String,
    val backdropPath: String,
    val overview: String,
    val tagline: String?,
    val crewMembers: List<CrewMember>,
    val duration: Int,
    val releaseDate: String
) {

  val director: CrewMember? by lazy {
    this.crewMembers.firstOrNull { it.job.equals("director", true) }
  }

  val chronology: String by lazy {
    try {
      releaseDate.split('-').first()
    } catch (ignored: NoSuchElementException) {
      String.empty()
    }
  }

}

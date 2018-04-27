package com.github.charbgr.cliffhanger.api_tmdb.entity

import com.github.charbgr.cliffhanger.domain.CrewMember

object CrewEntityMapper {

  fun transform(crewEntity: CrewEntity): CrewMember = CrewMember(crewEntity.id!!, crewEntity.name!!,
      crewEntity.job!!)

  fun transform(crewEntities: List<CrewEntity>?): List<CrewMember> =
      crewEntities?.map { transform(it) } ?: emptyList()
}

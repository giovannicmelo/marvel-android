package com.marvel.characters.mappers

import com.marvel.characters.frameworks.dtos.ComicDto
import com.marvel.core.characters.domain.Comic

object ComicMapper {

    fun toEntity(dto: ComicDto) = Comic(dto.name)

    fun toDto(entity: Comic) = ComicDto(entity.name)
}
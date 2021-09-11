package com.marvel.characters.mappers

import com.marvel.characters.frameworks.dtos.CharacterDto
import com.marvel.characters.frameworks.dtos.ComicListDto
import com.marvel.characters.frameworks.dtos.ImageDto
import com.marvel.characters.frameworks.dtos.ResultDto
import com.marvel.core.characters.domain.Character
import com.marvel.core.characters.domain.Image

object CharacterMapper {

    fun toEntity(dto: CharacterDto) = Character(
        id = dto.id,
        name = dto.name,
        description = dto.description,
        modified = dto.modified,
        thumbnail = ImageMapper.toEntity(dto.thumbnail ?: ImageDto()),
        resourceURI = dto.resourceURI,
        comics = dto.comics?.items?.map { ComicMapper.toEntity(it) }
    )

    fun toEntityList(dto: ResultDto<CharacterDto>): List<Character> = dto.data?.results?.map { toEntity(it) } ?: listOf()

    fun toDto(entity: Character) = CharacterDto(
        id = entity.id,
        name = entity.name,
        description = entity.description,
        modified = entity.modified,
        thumbnail = ImageMapper.toDto(entity.thumbnail ?: Image()),
        resourceURI = entity.resourceURI,
        comics = ComicListDto(entity.comics?.map { ComicMapper.toDto(it) })
    )
}
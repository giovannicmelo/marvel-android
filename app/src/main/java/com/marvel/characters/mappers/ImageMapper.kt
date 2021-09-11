package com.marvel.characters.mappers

import com.marvel.characters.frameworks.dtos.ImageDto
import com.marvel.core.characters.domain.Image

object ImageMapper {

    fun toEntity(dto: ImageDto) = Image(dto.path, dto.extension)

    fun toDto(entity: Image) = ImageDto(entity.path, entity.extension)
}
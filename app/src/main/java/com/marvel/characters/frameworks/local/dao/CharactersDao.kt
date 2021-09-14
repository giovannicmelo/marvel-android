package com.marvel.characters.frameworks.local.dao

import com.marvel.core.characters.domain.Character

private const val DATABASE_NAME = "characters"

object CharactersDao : PaperDao<List<Character>>(DATABASE_NAME)
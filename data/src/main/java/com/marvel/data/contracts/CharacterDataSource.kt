package com.marvel.data.contracts

import com.marvel.data.contracts.abstracts.DataSource
import com.marvel.data.dto.CharacterDTO

interface CharacterDataSource {

    interface Remote : DataSource.Remote<CharacterDTO>
}
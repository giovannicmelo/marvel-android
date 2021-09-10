package com.marvel.characters.ui.character

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.marvel.characters.base.BaseViewModel
import com.marvel.data.contracts.CharacterRepository
import com.marvel.data.dto.CharacterDTO
import com.marvel.data.enums.ApiResponseCode
import com.marvel.data.models.Character
import com.marvel.data.models.Comic
import kotlinx.coroutines.launch

class CharacterViewModel(private val repository: CharacterRepository) : BaseViewModel() {

    private val _charactersDTO = MutableLiveData<CharacterDTO>()

    val characters = Transformations.map(_charactersDTO) { dto ->
        dto.data?.results ?: run {
            emptyList<Character>()
        }
    }
    val character = Transformations.map(_charactersDTO) { dto ->
        dto.data?.results?.firstOrNull()
    }

    val comics = Transformations.map(character) { character ->
        var comicsList: List<Comic>? = null
        character?.let {
            if (it.comics?.items?.any() == true) {
                comicsList = it.comics?.items
            }
        }
        comicsList
    }

    fun getCharacterById(id: Int) = launch {
        val response = repository.getById(id)
        if (response.remote?.code == ApiResponseCode.SUCCESS.code) {
            response.content?.let {
                _charactersDTO.postValue(it)
            }
        } else {
            response.remote?.code?.let { setApiError(it, response.remote?.message) }
        }
    }

    fun getCharacters(query: String? = null) = launch {
        val response = repository.getAll(query)
        if (response.remote?.code == ApiResponseCode.SUCCESS.code) {
            response.content?.let {
                _charactersDTO.postValue(it)
            }
        } else {
            response.remote?.code?.let { setApiError(it, response.remote?.message) }
        }
    }
}
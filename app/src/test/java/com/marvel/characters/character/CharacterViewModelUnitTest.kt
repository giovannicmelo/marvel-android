package com.marvel.characters.character

import com.marvel.characters.ui.character.CharacterViewModel
import com.marvel.data.RepositoryObject
import com.marvel.data.contracts.CharacterRepository
import com.marvel.data.dto.CharacterDTO
import com.marvel.data.models.Character
import com.marvel.data.models.Data
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

@Ignore("Deprecated")
class CharacterViewModelUnitTest {

    private lateinit var viewModel: CharacterViewModel
    private lateinit var repositoryMock: CharacterRepository

    @Before
    fun setUp() {
        repositoryMock = mock()
        viewModel = CharacterViewModel(repositoryMock)
    }

    @Test
    fun `Get character by id, when is passed an id from an existent character, then shows a character`() {
        // Arrange
        val characterId = 1
        val expectedCharacter = CharacterDTO(
            data = Data(
                results = listOf(Character(characterId, "Char 1"))
            )
        )
        val repositoryObject = RepositoryObject(
            content = expectedCharacter
        )

        runBlocking {
            // Act
            whenever(repositoryMock.getById(characterId)).thenReturn(repositoryObject)
            viewModel.getCharacterById(characterId)

            // Assert
            verify(repositoryMock, times(1)).getById(characterId)
        }
    }
}
package com.marvel.core.characters.data

import com.marvel.core.characters.data.contracts.CharactersDataSource
import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersRepositoryTest {

    private lateinit var dataSourceMock: CharactersDataSource
    private lateinit var repository: CharactersRepositoryContract

    @Before
    fun setUp() {
        dataSourceMock = mock()
        repository = CharactersRepository(dataSourceMock)
    }

    @Test
    fun `Fetch all characters, returns a list of characters`() = runBlockingTest {
        // ARRANGE
        val expected = CharactersTestUtils.getCharactersList()
        whenever(dataSourceMock.getCharacters()).thenReturn(expected)

        // ACT
        val actual = repository.fetchAllCharacters()

        // ASSERT
        assertEquals(expected, actual)
    }

    @Test
    fun `Fetch all characters by name, when it is passed a name, then returns a list of characters`() = runBlockingTest {
        // ARRANGE
        val name = "Test"
        val expected = CharactersTestUtils.getCharactersList()
        whenever(dataSourceMock.getCharactersByName(name)).thenReturn(expected)

        // ACT
        val actual = repository.fetchAllCharactersByName(name)

        // ASSERT
        assertEquals(expected, actual)
    }

    @Test
    fun `Get character details, when it is passed an id, then returns a character`() = runBlockingTest {
        // ARRANGE
        val id = 1
        val expected = CharactersTestUtils.getCharacter()
        whenever(dataSourceMock.getCharacterById(id)).thenReturn(expected)

        // ACT
        val actual = repository.getCharacterDetails(id)

        // ASSERT
        assertEquals(expected, actual)
    }
}
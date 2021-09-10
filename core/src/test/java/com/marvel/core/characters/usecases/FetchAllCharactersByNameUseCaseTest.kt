package com.marvel.core.characters.usecases

import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchAllCharactersByNameUseCaseTest {

    private lateinit var repositoryMock: CharactersRepositoryContract
    private lateinit var useCase: FetchAllCharactersByNameUseCase

    @Before
    fun setUp() {
        repositoryMock = mock()
        useCase = FetchAllCharactersByNameUseCase(repositoryMock)
    }

    @Test
    fun `Fetch all characters by name, when it is passed a name, then returns a list of characters`() = runBlockingTest {
        // ARRANGE
        val name = "Test"
        val expected = CharactersTestUtils.getCharactersList()
        whenever(repositoryMock.fetchAllCharactersByName(name)).thenReturn(expected)

        // ACT
        val actual = useCase(name)

        // ASSERT
        assertEquals(expected, actual)
    }
}
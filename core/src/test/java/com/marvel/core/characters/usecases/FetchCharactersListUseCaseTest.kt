package com.marvel.core.characters.usecases

import CharactersTestUtils
import com.marvel.core.characters.data.contracts.CharactersRepositoryContract
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class FetchCharactersListUseCaseTest {

    private lateinit var repositoryMock: CharactersRepositoryContract
    private lateinit var useCase: FetchCharactersListUseCase

    @Before
    fun setUp() {
        repositoryMock = mock()
        useCase = FetchCharactersListUseCase(repositoryMock)
    }

    @Test
    fun `Fetch characters list, returns a list of firsts 20 characters`() = runBlockingTest {
        // ARRANGE
        val expected = CharactersTestUtils.getCharactersList()
        whenever(repositoryMock.fetchCharactersList()).thenReturn(expected)

        // ACT
        val actual = useCase()

        // ASSERT
        assertEquals(expected, actual)
    }

    @Test
    fun `Fetch characters list, returns a list of next 20 characters`() = runBlockingTest {
        // ARRANGE
        val nextPage = true
        val currentPage = 0
        val expected = CharactersTestUtils.getCharactersList()
        whenever(repositoryMock.fetchCharactersList(nextPage, currentPage)).thenReturn(expected)

        // ACT
        val actual = useCase(nextPage, currentPage)

        // ASSERT
        assertEquals(expected, actual)
    }
}
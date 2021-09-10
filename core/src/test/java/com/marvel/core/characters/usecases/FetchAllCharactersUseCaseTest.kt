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
class FetchAllCharactersUseCaseTest {

    private lateinit var repositoryMock: CharactersRepositoryContract
    private lateinit var useCase: FetchAllCharactersUseCase

    @Before
    fun setUp() {
        repositoryMock = mock()
        useCase = FetchAllCharactersUseCase(repositoryMock)
    }

    @Test
    fun `Fetch all events, returns a list of characters`() = runBlockingTest {
        // ARRANGE
        val expected = CharactersTestUtils.getCharactersList()
        whenever(repositoryMock.fetchAllCharacters()).thenReturn(expected)

        // ACT
        val actual = useCase()

        // ASSERT
        assertEquals(expected, actual)
    }
}
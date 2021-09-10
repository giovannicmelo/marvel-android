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
class GetCharacterDetailsUseCaseTest {

    private lateinit var repositoryMock: CharactersRepositoryContract
    private lateinit var useCase: GetCharacterDetailsUseCase

    @Before
    fun setUp() {
        repositoryMock = mock()
        useCase = GetCharacterDetailsUseCase(repositoryMock)
    }

    @Test
    fun `Get character details, when it is passed an id, then returns a character`() = runBlockingTest {
        // ARRANGE
        val id = 1
        val expected = CharactersTestUtils.getCharacter()
        whenever(repositoryMock.getCharacterDetails(id)).thenReturn(expected)

        // ACT
        val actual = useCase(id)

        // ASSERT
        assertEquals(expected, actual)
    }
}
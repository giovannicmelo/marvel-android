package com.marvel.data

import com.marvel.data.contracts.CharacterRepository
import com.marvel.data.utils.TestUtils
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CharacterRepositoryUnitTest {

    private lateinit var repositoryImplMock: CharacterRepository

    @Before
    fun setUp() {
        repositoryImplMock = mock()
    }

    @Test
    fun `Get all, when is requested to retrieve a list of characters, then returns a dto with a list of characters`() =
        runBlocking {
            // Arrange
            val expectedDTO = TestUtils.generateCharacterMock()

            whenever(repositoryImplMock.getAll()).thenReturn(expectedDTO)

            // Act
            val actualDTO = repositoryImplMock.getAll()

            // Assert
            Assert.assertEquals(
                "These objects are not the same",
                expectedDTO,
                actualDTO
            )
        }

    @Test
    fun `Get all, when is requested to retrieve a list of characters, then returns a empty dto`() =
        runBlocking {
            // Arrange
            val expectedDTO = TestUtils.generateEmptyMock()

            whenever(repositoryImplMock.getAll()).thenReturn(expectedDTO)

            // Act
            val actualDTO = repositoryImplMock.getAll()

            // Assert
            Assert.assertEquals(
                "These contents are not the same",
                expectedDTO.content,
                actualDTO.content
            )

            Assert.assertEquals(
                "These objects are not the same",
                expectedDTO,
                actualDTO
            )
        }

    @Test
    fun `Get by id, when is passed an id from an existent character, then returns a dto with a list of just one character`() =
        runBlocking {
            // Arrange
            val characterId = 1
            val expectedDTO = TestUtils.generateCharacterMock(characterId)

            whenever(repositoryImplMock.getById(characterId)).thenReturn(expectedDTO)

            // Act
            val actualDTO = repositoryImplMock.getById(characterId)

            // Assert
            Assert.assertEquals(
                "These objects are not the same",
                expectedDTO,
                actualDTO
            )
        }

    @Test
    fun `Get by id, when is passed an id from an non existent character, then returns a empty dto`() =
        runBlocking {
            // Arrange
            val expectedDTO = TestUtils.generateEmptyMock()

            whenever(repositoryImplMock.getById(0)).thenReturn(expectedDTO)

            // Act
            val actualDTO = repositoryImplMock.getById(0)

            // Assert
            Assert.assertEquals(
                "These contents are not the same",
                expectedDTO.content,
                actualDTO.content
            )

            Assert.assertEquals(
                "These objects are not the same",
                expectedDTO,
                actualDTO
            )
        }
}
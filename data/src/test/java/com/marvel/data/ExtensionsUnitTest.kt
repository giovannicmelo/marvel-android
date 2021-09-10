package com.marvel.data

import com.marvel.data.models.Character
import com.marvel.data.utils.fromJson
import com.marvel.data.utils.toJson
import com.marvel.data.utils.toMd5
import org.junit.Assert
import org.junit.Test

class ExtensionsUnitTest {

    @Test
    fun `To Md5, when a string digest is converted into a md5 hash, then returns a valid MD5 hash`() {
        val digest = "1abcd1234"
        val expectedHash = "ffd275c5130566a2916217b101f26150"
        val actualHash = digest.toMd5()
        Assert.assertEquals("This hash is not a md5 hash", expectedHash, actualHash)
    }

    @Test
    fun `To Md5, when a string digest is converted into a md5 hash, then returns a invalid MD5 hash`() {
        val digest = "1abcd1234"
        val expectedHash = "xxxxxxxxxxxxxxxxxxxxxx"
        val actualHash = digest.toMd5()
        Assert.assertNotEquals("This hash is a md5 hash", expectedHash, actualHash)
    }

    @Test
    fun `From Json, when a string is converted into a character class, then returns a character class instance`() {
        val jsonString = "{\"id\":1,\"name\":\"Test\"}"
        val expectedObject = Character(1, "Test")
        val actualObject = jsonString.fromJson<Character>()
        Assert.assertEquals("These objects are not the same", expectedObject, actualObject)
    }

    @Test
    fun `To Json, when a object is converted into a string json, then returns a valid json string`() {
        val character = Character(1, "Test")
        val expectedJsonString = "{\"id\":1,\"name\":\"Test\"}"
        val actualJsonString = character.toJson()
        Assert.assertEquals("These objects are not the same", expectedJsonString, actualJsonString)
    }
}
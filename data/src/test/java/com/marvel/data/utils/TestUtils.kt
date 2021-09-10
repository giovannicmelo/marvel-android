package com.marvel.data.utils

import com.marvel.data.DataSourceResponse
import com.marvel.data.MESSAGE_ERROR
import com.marvel.data.MESSAGE_SUCCESS
import com.marvel.data.REMOTE_ERROR
import com.marvel.data.RepositoryObject
import com.marvel.data.dto.CharacterDTO
import com.marvel.data.enums.ApiResponseCode
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

object TestUtils {

    private fun loadJsonFromFile(fileName: String): String? {
        var jsonStr: String? = null
        var inputStream: InputStream? = null

        try {
            inputStream = this.javaClass.classLoader?.getResourceAsStream(fileName)
            inputStream?.run {
                val size = this.available()
                val buffer = ByteArray(size)
                this.read(buffer)
                jsonStr = String(buffer, Charset.forName("UTF-8"))
            }
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        } finally {
            inputStream?.close()
        }

        return jsonStr
    }

    fun generateEmptyMock(): RepositoryObject<CharacterDTO> = RepositoryObject(
        remote = DataSourceResponse(
            code = REMOTE_ERROR,
            message = MESSAGE_ERROR
        ),
        content = CharacterDTO()
    )

    fun generateCharacterMock(id: Int = 0): RepositoryObject<CharacterDTO> {
        val fileName = if (id > 0) "character" else "characters"
        val mockJson = loadJsonFromFile("$fileName.json")?.fromJson<CharacterDTO>()
        return RepositoryObject(
            remote = DataSourceResponse(
                code = ApiResponseCode.SUCCESS.code,
                message = MESSAGE_SUCCESS
            ),
            content = mockJson
        )
    }
}

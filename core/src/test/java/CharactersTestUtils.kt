import com.marvel.core.characters.domain.Character
import com.marvel.core.characters.domain.Comic
import com.marvel.core.characters.domain.Image

object CharactersTestUtils {

    fun getComic() = Comic("Test")

    fun getComicsList() = listOf(getComic())

    fun getImage() = Image(
        path = "/test",
        extension = ".tst"
    )

    fun getCharacter() = Character(
        id = 1,
        description = "Test",
        name = "Test",
        thumbnail = getImage(),
        comics = getComicsList(),
        modified = "Test",
        resourceURI = "Test"
    )

    fun getCharactersList() = listOf(getCharacter())
}
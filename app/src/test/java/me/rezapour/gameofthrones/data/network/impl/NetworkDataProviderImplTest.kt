package me.rezapour.gameofthrones.data.network.impl

import com.google.common.io.Resources
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.data.network.NetWorkDataProvider
import me.rezapour.gameofthrones.data.network.apifake.RetrofitBuilderMock
import me.rezapour.gameofthrones.data.network.mapper.HouseDataMapper
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.mytask.util.MainCoroutineRule
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.junit.Assert.assertEquals
import java.io.File
import java.io.InputStream
import java.net.HttpURLConnection
import me.rezapour.gameofthrones.R
import me.rezapour.gameofthrones.model.charecter.CharacterDomain


class NetworkDataProviderImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    lateinit var mockWebServer: MockWebServer
    lateinit var dataProvider: NetWorkDataProvider

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        dataProvider = NetworkDataProviderImpl(
            RetrofitBuilderMock.provideApiService(mockWebServer),
            HouseDataMapper()
        )
    }

    @After
    fun destroy() {
        mockWebServer.shutdown()
    }

    private fun response(fileName: String): String {
        val inputStreamUser: InputStream =
            File(Resources.getResource(fileName).toURI()).inputStream()
        return inputStreamUser.bufferedReader().use { it.readText() }
    }

    @Test
    fun `getHouses response list of houses when run successfully`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(response("Houses.json"))
        mockWebServer.enqueue(responseTest)

        runBlocking {
            val response = dataProvider.getHouses()
            assertThat(response).isEqualTo(createDomainList())
        }
    }


    @Test
    fun `getHouses throws internet connection exception when api call is failed`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                dataProvider.getHouses()
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }


    @Test
    fun `getHouses throws access denied when response code is 400 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(response("Houses.json"))

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                dataProvider.getHouses()
            }
        }.messageId
        assertEquals(R.string.error_access_denied, messageId)
    }


    @Test
    fun `getHouses throws server error when response code is 500 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
            .setBody(response("Houses.json"))

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                dataProvider.getHouses()
            }
        }.messageId
        assertEquals(R.string.error_server_error, messageId)
    }

    @Test
    fun `getHouses throws internet connection problem when response code is unknown range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_MOVED_PERM)
            .setBody(response("Houses.json"))

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                dataProvider.getHouses()
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }


    @Test
    fun `getCharacter response CharacterDomain when run successfully`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(response("Character.json"))
        mockWebServer.enqueue(responseTest)

        runBlocking {
            val response =
                dataProvider.getCharacter("https://anapioficeandfire.com/api/characters/583")
            assertThat(response).isEqualTo(createCharacterDomain())
        }
    }

    @Test
    fun `getCharacter throws internet connection exception when api call is failed`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)

        mockWebServer.enqueue(responseTest)

        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                dataProvider.getCharacter("/")
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }


    @Test
    fun `getCharacter throws access denied when response code is 400 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
            .setBody(response("Character.json"))

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                dataProvider.getCharacter("/")
            }
        }.messageId
        assertEquals(R.string.error_access_denied, messageId)
    }


    @Test
    fun `getCharacter throws server error when response code is 500 range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_INTERNAL_ERROR)
            .setBody(response("Character.json"))

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                dataProvider.getCharacter("/")
            }
        }.messageId
        assertEquals(R.string.error_server_error, messageId)
    }

    @Test
    fun `getCharacter throws internet connection problem when response code is unknown range`() {
        val responseTest = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_MOVED_PERM)
            .setBody(response("Character.json"))

        mockWebServer.enqueue(responseTest)


        val messageId = Assert.assertThrows(DataProviderException::class.java) {
            runBlocking {
                dataProvider.getCharacter("/")
            }
        }.messageId
        assertEquals(R.string.error_internet_connection, messageId)
    }


    private fun createDomainList(): List<HouseDomain> {
        val house1 = HouseDomain(
            url = "https://www.anapioficeandfire.com/api/houses/1",
            name = "House Algood",
            region = "The Westerlands",
            coatOfArms = "A golden wreath, on a blue field with a gold border(Azure, a garland of laurel within a bordure or)",
            words = "",
            titles = arrayListOf(""),
            seats = arrayListOf(""),
            currentLord = "",
            heir = "",
            overlord = "https://www.anapioficeandfire.com/api/houses/229",
            founded = "",
            founder = "",
            diedOut = "",
            ancestralWeapons = arrayListOf(""),
            cadetBranches = arrayListOf(),
            swornMembers = arrayListOf()
        )


        val house2 = HouseDomain(
            url = "https://www.anapioficeandfire.com/api/houses/2",
            name = "House Allyrion of Godsgrace",
            region = "Dorne",
            coatOfArms = "Gyronny Gules and Sable, a hand couped Or",
            words = "No Foe May Pass",
            titles = arrayListOf(""),
            seats = arrayListOf("Godsgrace"),
            currentLord = "https://www.anapioficeandfire.com/api/characters/298",
            heir = "https://www.anapioficeandfire.com/api/characters/1922",
            overlord = "https://www.anapioficeandfire.com/api/houses/285",
            founded = "",
            founder = "",
            diedOut = "",
            ancestralWeapons = arrayListOf(""),
            cadetBranches = arrayListOf(),
            swornMembers = arrayListOf(
                "https://www.anapioficeandfire.com/api/characters/298",
                "https://www.anapioficeandfire.com/api/characters/1129",
                "https://www.anapioficeandfire.com/api/characters/1301",
                "https://www.anapioficeandfire.com/api/characters/1922"
            )
        )
        return arrayListOf(house1, house2)
    }


    private fun createCharacterDomain(): CharacterDomain {
        return CharacterDomain(
            url = "https://anapioficeandfire.com/api/characters/583",
            name = "Jon Snow",
            gender = "Male",
            culture = "Northmen",
            born = "In 283 AC",
            died = "",
            titles = arrayListOf("Lord Commander of the Night's Watch"),
            aliases = arrayListOf(
                "Lord Snow",
                "Ned Stark's Bastard",
                "The Snow of Winterfell",
                "The Crow-Come-Over",
                "The 998th Lord Commander of the Night's Watch",
                "The Bastard of Winterfell",
                "The Black Bastard of the Wall",
                "Lord Crow"
            ),
            father = "",
            mother = "",
            spouse = "",
            allegiances = arrayListOf("https://anapioficeandfire.com/api/houses/362"),
            books = arrayListOf("https://anapioficeandfire.com/api/books/5"),
            povBooks = arrayListOf(
                "https://anapioficeandfire.com/api/books/1",
                "https://anapioficeandfire.com/api/books/2",
                "https://anapioficeandfire.com/api/books/3",
                "https://anapioficeandfire.com/api/books/8"
            ),
            tvSeries = arrayListOf(
                "Season 1",
                "Season 2",
                "Season 3",
                "Season 4",
                "Season 5",
                "Season 6"
            ),
            playedBy = arrayListOf("Kit Harington")
        )
    }

}
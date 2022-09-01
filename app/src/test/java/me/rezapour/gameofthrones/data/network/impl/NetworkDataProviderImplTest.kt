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

}
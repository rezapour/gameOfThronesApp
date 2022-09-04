package me.rezapour.gameofthrones.data.repository.impl

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.data.network.NetWorkDataProvider
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.gameofthrones.model.house.HouseResponseDomain
import me.rezapour.mytask.util.MainCoroutineRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever


internal class HouseRepositoryImplTest {
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var dataProvider: NetWorkDataProvider

    private lateinit var repository: HouseRepositoryImpl

    @Before
    fun before() {
        dataProvider = mock()
        repository = HouseRepositoryImpl(dataProvider)
    }

    @Test
    fun `get houses return houseList when response is successful`() {
        runBlocking {
            whenever(dataProvider.getHouses("/")).thenReturn(createDomainList())

            repository.getHouses("/").test {
                assertThat(awaitItem()).isEqualTo(createDomainList())
                awaitComplete()
            }
            Mockito.verify(dataProvider, times(1)).getHouses("/")
        }
    }

    @Test
    fun `get houses unsuccessful when response has error`() {
        runBlocking {
            whenever(dataProvider.getHouses("/")).thenThrow(DataProviderException::class.java)

            repository.getHouses("/").test {
                assertThat(awaitError()).isInstanceOf(DataProviderException::class.java)
            }
            Mockito.verify(dataProvider, times(1)).getHouses("/")
        }
    }


    @Test
    fun `get character return characterDomain when response is successful`() {
        runBlocking {
            whenever(dataProvider.getCharacter("/")).thenReturn(createCharacterDomain())


            assertThat(repository.getCharacter("/")).isEqualTo(createCharacterDomain())


            Mockito.verify(dataProvider, times(1)).getCharacter("/")
        }
    }

    @Test
    fun `get character unsuccessful when response has error`() {
        runBlocking {
            whenever(dataProvider.getCharacter("/")).thenThrow(DataProviderException::class.java)

             Assert.assertThrows(DataProviderException::class.java) {
                runBlocking {
                    repository.getCharacter("/")
                }
            }

            Mockito.verify(dataProvider, times(1)).getCharacter("/")
        }
    }


    private fun createDomainList(): HouseResponseDomain {
        val house1 = HouseDomain(
            url = "https://www.anapioficeandfire.com/api/houses/1",
            name = "House Algood",
            region = "The Westerlands",
            coatOfArms = "A golden wreath, on a blue field with a gold border(Azure, a garland of laurel within a bordure or)",
            words = "",
            titles = arrayListOf(),
            seats = arrayListOf(),
            currentLord = "",
            heir = "",
            overlord = "https://www.anapioficeandfire.com/api/houses/229",
            founded = "",
            founder = "",
            diedOut = "",
            ancestralWeapons = arrayListOf(),
            cadetBranches = arrayListOf(),
            swornMembers = arrayListOf()
        )


        val house2 = HouseDomain(
            url = "https://www.anapioficeandfire.com/api/houses/2",
            name = "Startks",
            region = "north",
            coatOfArms = "winter is coming",
            words = "As High as Honor",
            titles = arrayListOf("king of the north", "Lord of the Eyrie"),
            seats = arrayListOf("Appleton"),
            currentLord = "https://www.anapioficeandfire.com/api/characters/894",
            heir = "https://www.anapioficeandfire.com/api/characters/477",
            overlord = "https://www.anapioficeandfire.com/api/houses/229",
            founded = "124 bc",
            founder = "Ed stark",
            diedOut = "",
            ancestralWeapons = arrayListOf(),
            cadetBranches = arrayListOf("north", "ws"),
            swornMembers = arrayListOf(
                "\"https://www.anapioficeandfire.com/api/characters/298",
                "https://www.anapioficeandfire.com/api/characters/1129"
            )
        )

        return HouseResponseDomain(
            "https://www.anapioficeandfire.com/api/houses?page=2&pageSize=10",
            arrayListOf(house1, house2)
        )
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
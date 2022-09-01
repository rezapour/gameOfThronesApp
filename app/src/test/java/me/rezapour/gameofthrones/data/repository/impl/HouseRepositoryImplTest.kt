package me.rezapour.gameofthrones.data.repository.impl

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.data.network.NetWorkDataProvider
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.mytask.util.MainCoroutineRule
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

    lateinit var dataProvider: NetWorkDataProvider

    lateinit var repository: HouseRepositoryImpl

    @Before
    fun before() {
        dataProvider = mock()
        repository = HouseRepositoryImpl(dataProvider)
    }

    @Test
    fun `get houses return cars when response is successful`() {
        runBlocking {
            whenever(dataProvider.getHouses()).thenReturn(createDomainList())

            repository.getHouses().test {
                assertThat(awaitItem()).isEqualTo(createDomainList())
                awaitComplete()
            }
            Mockito.verify(dataProvider, times(1)).getHouses()
        }
    }

    @Test
    fun `get houses unsuccessful when response has error`() {
        runBlocking {
            whenever(dataProvider.getHouses()).thenThrow(DataProviderException::class.java)

            repository.getHouses().test {
                assertThat(awaitError()).isInstanceOf(DataProviderException::class.java)
            }
            Mockito.verify(dataProvider, times(1)).getHouses()
        }
    }


    private fun createDomainList(): List<HouseDomain> {
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

        return arrayListOf(house1, house2)
    }


}
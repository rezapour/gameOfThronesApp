package me.rezapour.gameofthrones.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.data.repository.HouseRepository
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.gameofthrones.utils.DataState
import me.rezapour.mytask.util.MainCoroutineRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.lang.RuntimeException


class HousesListViewModelTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HousesListViewModel
    private lateinit var repository: HouseRepository

    @Before
    fun setUp() {
        repository = mock()
        viewModel = HousesListViewModel(repository)
    }

    @Test
    fun `get houses return house list when response is successful`() {
        runBlocking() {
            whenever(repository.getHouses()).thenReturn(createDomainList())
        }
        viewModel.getHouses()

        val valueRespond = viewModel.housesDataState.getOrAwaitValueTest()
        assertThat(valueRespond).isInstanceOf(DataState.Success::class.java)
    }

    @Test
    fun `get houses return Error when response is DataProviderException`() {
        runBlocking() {
            whenever(repository.getHouses()).thenThrow(DataProviderException::class.java)
        }
        viewModel.getHouses()
        val valueRespond = viewModel.housesDataState.getOrAwaitValueTest()
        assertThat(valueRespond).isInstanceOf(DataState.Error::class.java)
    }

    @Test
    fun `get houses return Error when response is Exception`() {
        runBlocking() {
            whenever(repository.getHouses()).thenThrow(RuntimeException::class.java)
        }
        viewModel.getHouses()
        val valueRespond = viewModel.housesDataState.getOrAwaitValueTest()
        assertThat(valueRespond).isInstanceOf(DataState.DefaultError::class.java)
    }


    private fun createDomainList(): Flow<List<HouseDomain>> = flow {
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

        emit(arrayListOf(house1, house2))
    }


}
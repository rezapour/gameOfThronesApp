package me.rezapour.gameofthrones.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.androiddevs.shoppinglisttestingyt.getOrAwaitValueTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.data.repository.HouseRepository
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.charecter.RequestData
import me.rezapour.gameofthrones.model.charecter.UiWidget
import me.rezapour.gameofthrones.model.charecter.UrlResponse
import me.rezapour.gameofthrones.utils.DataState
import me.rezapour.mytask.util.MainCoroutineRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


internal class HouseDetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: HouseDetailViewModel
    private lateinit var repository: HouseRepository

    @Before
    fun setUp() {
        repository = mock()
        viewModel = HouseDetailViewModel(repository)
    }

    @Test
    fun `get houses return house list when response is successful`() {
        runBlocking() {
            whenever(repository.getCharacter(anyString())).thenReturn(createCharacterDomain())
        }
        viewModel.loadUrls(createRequest())

        val valueRespond = viewModel.swornMembersDataState.getOrAwaitValueTest()
        Truth.assertThat(valueRespond).isInstanceOf(DataState.Success::class.java)
    }


    @Test
    fun `get houses throws exception when response is unsuccessful`() {
        runBlocking() {
            whenever(repository.getCharacter(anyString())).thenThrow((DataProviderException::class.java))
        }
        viewModel.loadUrls(createRequest())

        val valueRespond = viewModel.swornMembersDataState.getOrAwaitValueTest()
        Truth.assertThat(valueRespond).isInstanceOf(DataState.Success::class.java)

    }


    private fun createRequest(): ArrayList<RequestData> {
        val requestData1 = RequestData("/", UiWidget.FOUNDER)
        return arrayListOf(requestData1)
    }


    private fun createRespond(): List<UrlResponse> {
        val requestData1 = UrlResponse("Jon Snow", UiWidget.FOUNDER)

        return arrayListOf(requestData1)
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
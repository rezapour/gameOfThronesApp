package me.rezapour.gameofthrones.data.network.mapper

import com.google.common.truth.Truth.assertThat
import me.rezapour.gameofthrones.data.network.model.CharacterNetworkEntity
import me.rezapour.gameofthrones.data.network.model.HouseNetworkEntity
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.house.HouseDomain
import org.junit.Before
import org.junit.Test


internal class HouseDataMapperTest {

    lateinit var mapper: HouseDataMapper

    @Before
    fun setup() {
        mapper = HouseDataMapper()
    }

    @Test
    fun houseListNetworkEntityToListDomain() {
        assertThat(mapper.houseListNetworkEntityToListDomain(createEntityList())).isEqualTo(
            createDomainList()
        )
    }

    @Test
    fun characterNetworkEntityToCharacterDomain() {
        assertThat(mapper.characterNetworkEntityToDomain(createCharacterNetworkEntity())).isEqualTo(
            createCharacterDomain()
        )
    }


    private fun createEntityList(): List<HouseNetworkEntity> {
        val house1 = HouseNetworkEntity(
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


        val house2 = HouseNetworkEntity(
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


    private fun createCharacterNetworkEntity(): CharacterNetworkEntity {
        return CharacterNetworkEntity(
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
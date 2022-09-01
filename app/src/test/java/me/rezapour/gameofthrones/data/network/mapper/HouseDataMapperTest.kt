package me.rezapour.gameofthrones.data.network.mapper

import com.google.common.truth.Truth.assertThat
import me.rezapour.gameofthrones.data.network.model.HouseNetworkEntity
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


    fun createEntityList(): List<HouseNetworkEntity> {
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


    fun createDomainList(): List<HouseDomain> {
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
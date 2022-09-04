package me.rezapour.gameofthrones.data.network.mapper

import me.rezapour.gameofthrones.data.network.model.CharacterNetworkEntity
import me.rezapour.gameofthrones.data.network.model.HouseNetworkEntity
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.house.HouseDomain
import javax.inject.Inject

class HouseDataMapper @Inject constructor() {
    private fun houseNetworkEntityToDomain(entity: HouseNetworkEntity): HouseDomain {
        return HouseDomain(
            url = entity.url,
            name = entity.name,
            region = entity.region,
            coatOfArms = entity.coatOfArms,
            words = entity.words,
            titles = entity.titles,
            seats = entity.seats,
            currentLord = entity.currentLord,
            heir = entity.heir,
            overlord = entity.overlord,
            founded = entity.founded,
            founder = entity.founder,
            diedOut = entity.diedOut,
            ancestralWeapons = entity.ancestralWeapons,
            cadetBranches = entity.cadetBranches,
            swornMembers = entity.swornMembers
        )
    }


    fun houseListNetworkEntityToListDomain(entityList: List<HouseNetworkEntity>): List<HouseDomain> {
        return entityList.map { entity -> houseNetworkEntityToDomain(entity) }
    }


    fun characterNetworkEntityToDomain(entity: CharacterNetworkEntity): CharacterDomain {
        return CharacterDomain(
            url = entity.url,
            name = entity.name,
            gender = entity.gender,
            culture = entity.culture,
            born = entity.born,
            died = entity.died,
            titles = entity.titles,
            aliases = entity.aliases,
            father = entity.father,
            mother = entity.mother,
            spouse = entity.spouse,
            allegiances = entity.allegiances,
            books = entity.books,
            povBooks = entity.povBooks,
            tvSeries = entity.tvSeries,
            playedBy = entity.playedBy
        )
    }

}
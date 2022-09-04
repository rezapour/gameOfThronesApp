package me.rezapour.gameofthrones.data.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.rezapour.gameofthrones.data.network.NetWorkDataProvider
import me.rezapour.gameofthrones.data.repository.HouseRepository
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.gameofthrones.model.house.HouseResponseDomain

class HouseRepositoryImpl(private val dataProvider: NetWorkDataProvider) : HouseRepository {
    override suspend fun getHouses(url: String): Flow<HouseResponseDomain> = flow {
        val responseDomain = dataProvider.getHouses(url)
        emit(responseDomain)
    }

    override suspend fun getCharacter(url: String): CharacterDomain {
        return dataProvider.getCharacter(url)

    }
}
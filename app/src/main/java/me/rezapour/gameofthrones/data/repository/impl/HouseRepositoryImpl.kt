package me.rezapour.gameofthrones.data.repository.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.rezapour.gameofthrones.data.network.NetWorkDataProvider
import me.rezapour.gameofthrones.data.repository.HouseRepository
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.house.HouseDomain

class HouseRepositoryImpl(private val dataProvider: NetWorkDataProvider) : HouseRepository {
    override suspend fun getHouses(): Flow<List<HouseDomain>> = flow {
        val housesList = dataProvider.getHouses()
        emit(housesList)
    }

    override suspend fun getCharacter(url: String): Flow<CharacterDomain> = flow {
        val character = dataProvider.getCharacter(url)
        emit(character)
    }
}
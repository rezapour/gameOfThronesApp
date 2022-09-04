package me.rezapour.gameofthrones.data.repository

import kotlinx.coroutines.flow.Flow
import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.gameofthrones.model.house.HouseResponseDomain

interface HouseRepository {
    @Throws(DataProviderException::class)
    suspend fun getHouses(url: String): Flow<HouseResponseDomain>

    @Throws(DataProviderException::class)
    suspend fun getCharacter(url: String): CharacterDomain
}
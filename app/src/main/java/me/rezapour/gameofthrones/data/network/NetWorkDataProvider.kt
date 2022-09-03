package me.rezapour.gameofthrones.data.network

import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.house.HouseDomain

interface NetWorkDataProvider {
    @Throws(DataProviderException::class)
    suspend fun getHouses(): List<HouseDomain>

    @Throws(DataProviderException::class)
    suspend fun getCharacter(url: String): CharacterDomain
}
package me.rezapour.gameofthrones.data.network

import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.house.HouseDomain
import me.rezapour.gameofthrones.model.house.HouseResponseDomain

interface NetWorkDataProvider {
    @Throws(DataProviderException::class)
    suspend fun getHouses(url:String): HouseResponseDomain

    @Throws(DataProviderException::class)
    suspend fun getCharacter(url: String): CharacterDomain
}
package me.rezapour.gameofthrones.data.network

import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.model.house.HouseDomain

interface NetWorkDataProvider {
    @Throws(DataProviderException::class)
    suspend fun getHouses(): List<HouseDomain>
}
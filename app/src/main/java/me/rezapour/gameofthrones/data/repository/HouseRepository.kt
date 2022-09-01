package me.rezapour.gameofthrones.data.repository

import kotlinx.coroutines.flow.Flow
import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.model.house.HouseDomain

interface HouseRepository {
   @Throws(DataProviderException::class)
   suspend fun getHouses(): Flow<List<HouseDomain>>
}
package me.rezapour.gameofthrones.data.network

import me.rezapour.gameofthrones.model.house.HouseDomain

interface NetWorkDataProvider {

    suspend fun getHouses(): List<HouseDomain>
}
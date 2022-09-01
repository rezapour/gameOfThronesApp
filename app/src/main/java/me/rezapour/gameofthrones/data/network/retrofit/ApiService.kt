package me.rezapour.gameofthrones.data.network.retrofit

import me.rezapour.gameofthrones.data.network.model.HouseNetworkEntity
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("houses")
   suspend fun getHouses(): Response<List<HouseNetworkEntity>>
}
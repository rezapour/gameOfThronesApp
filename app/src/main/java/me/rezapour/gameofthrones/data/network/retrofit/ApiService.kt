package me.rezapour.gameofthrones.data.network.retrofit

import me.rezapour.gameofthrones.data.network.model.CharacterNetworkEntity
import me.rezapour.gameofthrones.data.network.model.HouseNetworkEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET()
    suspend fun getHouses(@Url url: String): Response<List<HouseNetworkEntity>>

    @GET()
    suspend fun getCharacter(@Url url: String): Response<CharacterNetworkEntity>
}
package me.rezapour.gameofthrones.data.network.impl

import me.rezapour.gameofthrones.data.exception.DataProviderException
import me.rezapour.gameofthrones.data.network.NetWorkDataProvider
import me.rezapour.gameofthrones.data.network.exception.ExceptionMapper
import me.rezapour.gameofthrones.data.network.mapper.HouseDataMapper
import me.rezapour.gameofthrones.data.network.retrofit.ApiService
import me.rezapour.gameofthrones.model.charecter.CharacterDomain
import me.rezapour.gameofthrones.model.house.HouseDomain
import retrofit2.Response

class NetworkDataProviderImpl(
    private val apiService: ApiService,
    private val mapper: HouseDataMapper
) : NetWorkDataProvider {

    override suspend fun getHouses(): List<HouseDomain> {
        try {
            val response = apiService.getHouses()
            if (response.isSuccessful) {
                if (response.isResponseValid())
                    return mapper.houseListNetworkEntityToListDomain(response.body()!!)
                else
                    throw DataProviderException(ExceptionMapper.toServerError())
            } else {
                throw DataProviderException(ExceptionMapper.toApiCallErrorMessage(response.code()))
            }

        } catch (e: Exception) {
            if (e is DataProviderException)
                throw e
            throw DataProviderException(ExceptionMapper.toInternetConnectionError())
        }
    }

    override suspend fun getCharacter(url: String): CharacterDomain {
        try {
            val response = apiService.getCharacter(url)
            if (response.isSuccessful) {
                if (response.isResponseValid()) {
                    return mapper.characterNetworkEntityToDomain(response.body()!!)
                } else {
                    throw DataProviderException(ExceptionMapper.toServerError())
                }
            } else {
                throw DataProviderException(ExceptionMapper.toApiCallErrorMessage(response.code()))
            }
        } catch (e: Exception) {
            if (e is DataProviderException)
                throw e
            throw DataProviderException(ExceptionMapper.toInternetConnectionError())
        }
    }

    private fun <T> Response<T>.isResponseValid(): Boolean {
        return body() != null
    }
}
package me.rezapour.gameofthrones.di.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.rezapour.gameofthrones.data.network.Impl.NetworkDataProviderImpl
import me.rezapour.gameofthrones.data.network.NetWorkDataProvider
import me.rezapour.gameofthrones.data.network.mapper.HouseDataMapper
import me.rezapour.gameofthrones.data.network.retrofit.ApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkDataProviderModule {

    @Singleton
    @Provides
    fun networkDataProvider(apiService: ApiService, mapper: HouseDataMapper): NetWorkDataProvider {
        return NetworkDataProviderImpl(apiService, mapper)
    }
}
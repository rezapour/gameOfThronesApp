package me.rezapour.gameofthrones.di.repository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.rezapour.gameofthrones.data.network.NetWorkDataProvider
import me.rezapour.gameofthrones.data.repository.HouseRepository
import me.rezapour.gameofthrones.data.repository.impl.HouseRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HousesRepositoryModule {
    @Singleton
    @Provides
    fun housesRepositoryProvider(dataProvider: NetWorkDataProvider): HouseRepository {
        return HouseRepositoryImpl(dataProvider)
    }
}
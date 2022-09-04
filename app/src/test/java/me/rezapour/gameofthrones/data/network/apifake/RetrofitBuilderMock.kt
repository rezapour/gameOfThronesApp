package me.rezapour.gameofthrones.data.network.apifake


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import me.rezapour.gameofthrones.assets.AppConstants
import me.rezapour.gameofthrones.data.network.retrofit.ApiService
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitBuilderMock {


    private fun gsonConverterFactoryProvider(): GsonConverterFactory {
        return GsonConverterFactory.create(gsonBuilderProvider())
    }


    private fun gsonBuilderProvider(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }


    private fun okhttpClientProvider(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(AppConstants.TIME_OUT, TimeUnit.MILLISECONDS)
            .readTimeout(AppConstants.TIME_OUT, TimeUnit.MILLISECONDS)
            .writeTimeout(AppConstants.TIME_OUT, TimeUnit.MILLISECONDS)
            .build()
    }


    fun provideApiService(mockWebServer: MockWebServer): ApiService {
        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(gsonConverterFactoryProvider())
            .client(okhttpClientProvider())
            .build().create(ApiService::class.java)
    }
}

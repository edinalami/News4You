package hu.bme.aut.news4you.network

import dagger.Module
import dagger.Provides
import hu.bme.aut.news4you.network.api.NewsApi
import hu.bme.aut.news4you.network.api.ReadingListApi
import hu.bme.aut.news4you.network.api.UserApi
import javax.inject.Singleton

@Module
class NetworkModule {
    private val authName = "apikey"
    private val apiKey = "gPo7QFQ1U2mFitiGAhFRx1mtvtPKQxun"

    @Provides
    @Singleton
    fun provideApiClient(): ApiClient = ApiClient(authName, apiKey = apiKey)

    @Provides
    @Singleton
    fun provideNewsApi(apiClient: ApiClient): NewsApi = apiClient.createService(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideReadingListApi(apiClient: ApiClient): ReadingListApi =
        apiClient.createService(ReadingListApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(apiClient: ApiClient): UserApi = apiClient.createService(UserApi::class.java)
}
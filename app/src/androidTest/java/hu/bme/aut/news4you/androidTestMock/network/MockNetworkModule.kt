package hu.bme.aut.news4you.androidTestMock.network

import dagger.Module
import dagger.Provides
import hu.bme.aut.news4you.network.api.NewsApi
import hu.bme.aut.news4you.network.api.ReadingListApi
import hu.bme.aut.news4you.network.api.UserApi
import javax.inject.Singleton

@Module
class MockNetworkModule {
    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi = MockNewsApi()

    @Provides
    @Singleton
    fun provideReadingListApi(): ReadingListApi = MockReadingListApi()

    @Provides
    @Singleton
    fun provideUserApi(): UserApi = MockUserApi()
}
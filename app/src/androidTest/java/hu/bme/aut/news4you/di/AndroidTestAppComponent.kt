package hu.bme.aut.news4you.di

import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import dagger.Component
import hu.bme.aut.news4you.androidTestMock.network.MockNetworkModule
import hu.bme.aut.news4you.database.DiskModule
import hu.bme.aut.news4you.test.DetailsAndroidTest
import hu.bme.aut.news4you.test.HomeAndroidTest
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidTestApplicationModule::class,
        DiskModule::class,
        MockNetworkModule::class,
        RainbowCakeModule::class,
        ViewModelModule::class
    ]
)
interface AndroidTestAppComponent : AppComponent {
    fun inject(test: HomeAndroidTest)
    fun inject(test: DetailsAndroidTest)
}
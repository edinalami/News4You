package hu.bme.aut.news4you.di

import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import dagger.Component
import hu.bme.aut.news4you.database.DiskModule
import hu.bme.aut.news4you.mockAndroidTest.network.MockNetworkModule
import hu.bme.aut.news4you.test.HomeTest
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
    fun inject(test: HomeTest)
}
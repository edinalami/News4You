package hu.bme.aut.news4you.di

import co.zsmb.rainbowcake.dagger.RainbowCakeModule
import dagger.Component
import hu.bme.aut.news4you.database.DiskModule
import hu.bme.aut.news4you.mockTest.network.MockNetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        TestApplicationModule::class,
        DiskModule::class,
        MockNetworkModule::class,
        RainbowCakeModule::class,
        ViewModelModule::class
    ]
)
interface TestAppComponent : AppComponent
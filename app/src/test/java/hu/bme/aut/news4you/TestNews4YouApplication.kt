package hu.bme.aut.news4you

import hu.bme.aut.news4you.di.DaggerTestAppComponent
import hu.bme.aut.news4you.di.TestApplicationModule

class TestNews4YouApplication : News4YouApplication() {

    override fun setupInjector() {
        injector = DaggerTestAppComponent
            .builder()
            .testApplicationModule(
                TestApplicationModule(
                    this
                )
            )
            .build()
    }

}
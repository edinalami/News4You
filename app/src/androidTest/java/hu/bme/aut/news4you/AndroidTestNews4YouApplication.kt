package hu.bme.aut.news4you

import hu.bme.aut.news4you.di.AndroidTestApplicationModule
import hu.bme.aut.news4you.di.DaggerAndroidTestAppComponent

class AndroidTestNews4YouApplication: News4YouApplication() {

    override fun setupInjector() {
        injector = DaggerAndroidTestAppComponent
            .builder()
            .androidTestApplicationModule(
                AndroidTestApplicationModule(
                    this
                )
            )
            .build()
    }

}
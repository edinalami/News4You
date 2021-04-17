package hu.bme.aut.news4you.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.bme.aut.news4you.utils.EspressoTest
import hu.bme.aut.news4you.MainActivity
import hu.bme.aut.news4you.di.AndroidTestAppComponent
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeTest : EspressoTest<MainActivity>(MainActivity::class.java) {

    override fun injectDependencies(androidTestAppComponent: AndroidTestAppComponent) {
        androidTestAppComponent.inject(this)
    }

}
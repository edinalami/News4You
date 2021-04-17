package hu.bme.aut.news4you.utils

import android.app.Activity
import android.content.Context
import androidx.annotation.CallSuper
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import hu.bme.aut.news4you.AndroidTestNews4YouApplication
import hu.bme.aut.news4you.di.AndroidTestAppComponent
import org.junit.Before
import org.junit.BeforeClass
import org.junit.Rule
import java.io.File
import java.util.*

abstract class EspressoTest<T : Activity?>(activityClass: Class<T>?) {

    @Rule
    var activityScenarioRule: ActivityScenarioRule<T> = ActivityScenarioRule(activityClass)

    init {
        val application: AndroidTestNews4YouApplication = InstrumentationRegistry.getInstrumentation()
            .targetContext
            .applicationContext as AndroidTestNews4YouApplication
        injectDependencies(application.injector as AndroidTestAppComponent)
    }

    @BeforeClass
    fun beforeClass() {
        InstrumentationRegistry.getInstrumentation().targetContext
            .deleteDatabase("news4you.db")
    }

    @Before
    @CallSuper
    fun setup() {
        clearAppState()
    }

    private fun clearAppState() {
        val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val root: File? = targetContext.filesDir.parentFile
        val sharedPreferencesFileNames: Array<String>? =
            File(root, "shared_prefs").list()
        if (sharedPreferencesFileNames == null || sharedPreferencesFileNames.isEmpty()) {
            return
        }
        for (fileName in Objects.requireNonNull(sharedPreferencesFileNames)) {
            targetContext
                .getSharedPreferences(
                    fileName.replace(".xml", ""),
                    Context.MODE_PRIVATE
                )
                .edit().clear().commit()
        }
    }

    abstract fun injectDependencies(androidTestAppComponent: AndroidTestAppComponent)

}
package hu.bme.aut.news4you.utils

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import hu.bme.aut.news4you.AndroidTestNews4YouApplication
import kotlin.jvm.Throws

class ApplicationTestRunner : AndroidJUnitRunner() {

    @Throws(
        InstantiationException::class,
        IllegalAccessException::class,
        ClassNotFoundException::class
    )
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, AndroidTestNews4YouApplication::class.java.name, context)
    }

}
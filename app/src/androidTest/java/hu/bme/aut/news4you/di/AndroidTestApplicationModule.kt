package hu.bme.aut.news4you.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidTestApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

}
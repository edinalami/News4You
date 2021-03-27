package hu.bme.aut.news4you.di

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(@get:Provides @Singleton val context: Context)
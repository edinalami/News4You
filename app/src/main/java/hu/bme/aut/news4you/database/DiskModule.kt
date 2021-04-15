package hu.bme.aut.news4you.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import hu.bme.aut.news4you.database.dao.ArticleDao
import hu.bme.aut.news4you.database.dao.UserDao
import javax.inject.Singleton

@Module
class DiskModule {
    companion object {
        private const val DB_NAME = "news4you.db"
    }

    @Provides
    @Singleton
    fun provideArticleDao(db: AppDatabase): ArticleDao = db.articleDao()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}
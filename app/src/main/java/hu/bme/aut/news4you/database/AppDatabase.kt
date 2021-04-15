package hu.bme.aut.news4you.database

import androidx.room.Database
import androidx.room.RoomDatabase
import hu.bme.aut.news4you.database.dao.ArticleDao
import hu.bme.aut.news4you.database.dao.UserDao
import hu.bme.aut.news4you.database.model.RoomArticle
import hu.bme.aut.news4you.database.model.RoomUser

@Database(
    entities = [RoomArticle::class, RoomUser::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun userDao(): UserDao

}
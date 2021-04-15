package hu.bme.aut.news4you.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hu.bme.aut.news4you.database.model.RoomArticle

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    fun getAllArticles(): List<RoomArticle>

    @Query("SELECT * FROM articles WHERE id=:id")
    fun getArticleById(id: String): RoomArticle?

    @Query("SELECT * FROM articles WHERE latest = 1")
    fun getCachedArticles(): List<RoomArticle>

    @Query("SELECT * FROM articles WHERE saved = 1")
    fun getSavedArticles(): List<RoomArticle>

    @Query("UPDATE articles SET latest = 1 WHERE id=:id")
    fun updateArticleSetCached(id: String)

    @Query("UPDATE articles SET latest = 0")
    fun updateArticlesSetUnCached()

    @Query("UPDATE articles SET saved = 1 WHERE id=:id")
    fun updateArticleSetSaved(id: String)

    @Query("UPDATE articles SET saved = 0 WHERE id=:id")
    fun updateArticleSetUnSaved(id: String)

    @Query("DELETE FROM articles WHERE latest = 1 AND saved = 0")
    fun deleteCachedArticles()

    @Query("DELETE FROM articles WHERE id=:id AND saved = 0 AND latest = 0")
    fun deleteArticle(id: String)

    @Query("DELETE FROM articles WHERE saved = 1 AND latest = 0")
    fun deleteSavedArticles()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertArticle(article: RoomArticle)
}
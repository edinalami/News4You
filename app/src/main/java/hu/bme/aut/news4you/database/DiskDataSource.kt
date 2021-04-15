package hu.bme.aut.news4you.database

import hu.bme.aut.news4you.database.dao.ArticleDao
import hu.bme.aut.news4you.database.dao.UserDao
import hu.bme.aut.news4you.database.model.RoomArticle
import hu.bme.aut.news4you.database.model.RoomUser
import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.interactor.model.DomainUser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DiskDataSource @Inject constructor(
    private val articleDao: ArticleDao,
    private val userDao: UserDao
) {
    fun getLatestNews(): List<DomainArticle> {
        return articleDao.getCachedArticles().map(RoomArticle::toDomainArticle)
    }

    fun getSavedNews(): List<DomainArticle> {
        return articleDao.getSavedArticles().map(RoomArticle::toDomainArticle)
    }

    fun getArticle(id: String): DomainArticle? {
        return articleDao.getArticleById(id)?.toDomainArticle()
    }

    fun saveArticle(id: String) {
        articleDao.updateArticleSetSaved(id)
    }

    fun deleteArticle(id: String) {
        articleDao.updateArticleSetUnSaved(id)
        articleDao.deleteArticle(id)
    }

    fun populateCachedNews(articles: List<DomainArticle>) {
        articleDao.deleteCachedArticles()
        articleDao.updateArticlesSetUnCached()
        for (article in articles) {
            val roomArticle = article.toRoomArticle(latest = true)
            articleDao.insertArticle(roomArticle)
            articleDao.updateArticleSetCached(roomArticle.id)
        }
    }

    fun populateSavedNews(articles: List<DomainArticle>) {
        articleDao.deleteSavedArticles()
        for (article in articles) {
            val roomArticle = article.toRoomArticle(saved = true)
            articleDao.insertArticle(roomArticle)
            articleDao.updateArticleSetSaved(roomArticle.id)
        }
    }

    fun getUser(): DomainUser? {
        return userDao.getUser()?.toDomainUser()
    }

    fun insertUser(domainUser: DomainUser): DomainUser {
        val id = userDao.insertUser(RoomUser(username = domainUser.username))
        return DomainUser(id, domainUser.username)
    }

    fun updateUser(domainUser: DomainUser) {
        userDao.updateUser(domainUser.toRoomUser())
    }

    fun deleteUser(domainUser: DomainUser) {
        userDao.deleteUser(domainUser.toRoomUser())
    }
}

package hu.bme.aut.news4you.test

import hu.bme.aut.news4you.database.DiskDataSource
import hu.bme.aut.news4you.database.dao.ArticleDao
import hu.bme.aut.news4you.database.dao.UserDao
import hu.bme.aut.news4you.testMock.createCachedRoomArticle
import hu.bme.aut.news4you.testMock.createSavedRoomArticle
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class DiskDataSourceTest {
    var articleDao: ArticleDao = mockk()
    var userDao: UserDao = mockk()

    lateinit var diskDataSource: DiskDataSource

    @Before
    fun setup() {
        diskDataSource = DiskDataSource(articleDao, userDao)
    }

    @Test
    fun testGetLatestNews() {
        every {
            articleDao.getCachedArticles()
        } returns listOf(createCachedRoomArticle())

        val articles = diskDataSource.getLatestNews()
        Assert.assertEquals(1, articles.size)
    }

    @Test
    fun testGetSavedNews() {
        every {
            articleDao.getSavedArticles()
        } returns listOf(createSavedRoomArticle())

        val articles = diskDataSource.getSavedNews()
        Assert.assertEquals(1, articles.size)
    }
}
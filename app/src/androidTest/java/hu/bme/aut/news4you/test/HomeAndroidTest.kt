package hu.bme.aut.news4you.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.bme.aut.news4you.EspressoTest
import hu.bme.aut.news4you.MainActivity
import hu.bme.aut.news4you.di.AndroidTestAppComponent
import hu.bme.aut.news4you.interactor.AppInteractor
import hu.bme.aut.news4you.ui.home.HomePresenter
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class HomeAndroidTest : EspressoTest<MainActivity>(MainActivity::class.java) {

    override fun injectDependencies(injector: AndroidTestAppComponent) {
        injector.inject(this)
    }

    @Inject
    lateinit var appInteractor: AppInteractor

    var homePresenter: HomePresenter = HomePresenter(appInteractor)

    @Test
    fun testGetLatestContent() = runBlocking {
        val data = homePresenter.getContent()
        Assert.assertEquals(3, data!![0].size)
    }

    @Test
    fun testGetSavedContent() = runBlocking {
        val data = homePresenter.getContent()
        Assert.assertEquals(3, data!![1].size)
    }

    @Test
    fun testSaveArticle() = runBlocking {
        var latestArticles = homePresenter.getContent()!![0]
        homePresenter.saveArticle(latestArticles[0])

        val articles = homePresenter.getContent()

        latestArticles = articles!![0]
        val savedArticles = articles[1]

        Assert.assertEquals(2, latestArticles.size)
        Assert.assertEquals(4, savedArticles.size)
    }

    @Test
    fun testDeleteArticle() = runBlocking {
        var savedArticles = homePresenter.getContent()!![1]
        homePresenter.deleteArticle(savedArticles[0].uri)

        val articles = homePresenter.getContent()

        savedArticles = articles!![1]
        val latestArticles = articles[0]

        Assert.assertEquals(2, savedArticles.size)
        Assert.assertEquals(4, latestArticles.size)
    }

}
package hu.bme.aut.news4you.test

import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.bme.aut.news4you.EspressoTest
import hu.bme.aut.news4you.MainActivity
import hu.bme.aut.news4you.androidTestMock.createLatestUIArticle
import hu.bme.aut.news4you.di.AndroidTestAppComponent
import hu.bme.aut.news4you.interactor.AppInteractor
import hu.bme.aut.news4you.ui.details.DetailsPresenter
import hu.bme.aut.news4you.ui.model.ArticleState
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class DetailsAndroidTest : EspressoTest<MainActivity>(MainActivity::class.java) {

    override fun injectDependencies(injector: AndroidTestAppComponent) {
        injector.inject(this)
    }

    @Inject
    lateinit var appInteractor: AppInteractor

    var detailsPresenter: DetailsPresenter = DetailsPresenter(appInteractor)

    @Test
    fun testSaveArticle() = runBlocking {
        delay(5000) //without delay:

        val article = createLatestUIArticle()

        detailsPresenter.saveArticle(article)

        val state = detailsPresenter.getArticleState(article.uri)

        Assert.assertEquals(ArticleState.SAVED, state)
    }

    @Test
    fun testDeleteArticleAlsoLatest() = runBlocking {
        delay(5000) //without delay: racecondition

        detailsPresenter.deleteArticle("nyt://article/4b1efa4e-8c05-5f99-acef-725fe59385e9")

        val state =
            detailsPresenter.getArticleState("nyt://article/4b1efa4e-8c05-5f99-acef-725fe59385e9")

        Assert.assertEquals(ArticleState.LATEST, state)
    }

    @Test
    fun testDeleteArticleNotLatest() = runBlocking {
        delay(5000) //without delay: racecondition

        detailsPresenter.deleteArticle("nyt://article/fbaa08c4-2c82-553f-ae7c-300d91aaef85")

        val state =
            detailsPresenter.getArticleState("nyt://article/fbaa08c4-2c82-553f-ae7c-300d91aaef85")

        Assert.assertEquals(ArticleState.NONE, state)
    }
}
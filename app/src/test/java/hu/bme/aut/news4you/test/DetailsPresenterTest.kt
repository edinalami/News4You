package hu.bme.aut.news4you.test

import co.zsmb.rainbowcake.test.base.PresenterTest
import hu.bme.aut.news4you.interactor.AppInteractor
import hu.bme.aut.news4you.testMock.createLatestUIArticle
import hu.bme.aut.news4you.testMock.createSavedUIArticle
import hu.bme.aut.news4you.ui.details.DetailsPresenter
import hu.bme.aut.news4you.util.network.DataTransferSuccess
import hu.bme.aut.news4you.util.network.NetworkUnavailableNotCached
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailsPresenterTest : PresenterTest() {
    var appInteractor: AppInteractor = mockk()

    lateinit var detailsPresenter: DetailsPresenter

    @Before
    fun setup() {
        detailsPresenter = DetailsPresenter(appInteractor)
    }

    @Test
    fun testSaveArticleSuccess() = runBlockingTest {
        coEvery {
            appInteractor.saveArticle(createLatestUIArticle())
        } returns DataTransferSuccess(true)

        val status = detailsPresenter.saveArticle(createLatestUIArticle())
        Assert.assertEquals(true, status)
    }

    @Test
    fun testSaveArticleFailure() = runBlockingTest {
        coEvery {
            appInteractor.saveArticle(createLatestUIArticle())
        } returns NetworkUnavailableNotCached

        val status = detailsPresenter.saveArticle(createLatestUIArticle())
        Assert.assertEquals(null, status)
    }

    @Test
    fun testDeleteArticleSuccess() = runBlockingTest {
        coEvery {
            appInteractor.deleteArticle(createSavedUIArticle().uri)
        } returns DataTransferSuccess(true)

        val status = detailsPresenter.deleteArticle(createSavedUIArticle().uri)
        Assert.assertEquals(true, status)
    }

    @Test
    fun testDeleteArticleFailure() = runBlockingTest {
        coEvery {
            appInteractor.deleteArticle(createSavedUIArticle().uri)
        } returns NetworkUnavailableNotCached

        val status = detailsPresenter.deleteArticle(createSavedUIArticle().uri)
        Assert.assertEquals(null, status)
    }
}
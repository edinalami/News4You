package hu.bme.aut.news4you.test

import co.zsmb.rainbowcake.test.base.PresenterTest
import hu.bme.aut.news4you.interactor.AppInteractor
import hu.bme.aut.news4you.testMock.createLatestUIArticle
import hu.bme.aut.news4you.testMock.createSavedUIArticle
import hu.bme.aut.news4you.ui.home.HomePresenter
import hu.bme.aut.news4you.ui.model.ArticleState
import hu.bme.aut.news4you.util.network.DataTransferSuccess
import hu.bme.aut.news4you.util.network.NetworkUnavailableNotCached
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomePresenterTest : PresenterTest() {
    var appInteractor: AppInteractor = mockk()

    lateinit var homePresenter: HomePresenter

    @Before
    fun setup() {
        homePresenter = HomePresenter(appInteractor)
    }

    @Test
    fun testGetContentUnitTestSuccess() = runBlockingTest {
        coEvery {
            appInteractor.getLatestNews()
        } returns DataTransferSuccess(listOf(createLatestUIArticle()))
        coEvery {
            appInteractor.getSavedNews()
        } returns DataTransferSuccess(listOf(createSavedUIArticle()))

        val data = homePresenter.getContent()

        assertEquals(2, data?.size)
        assertEquals(1, data!![0].size)
        assertEquals(1, data[1].size)
        assertEquals(ArticleState.LATEST, data[0].first().state)
        assertEquals(ArticleState.SAVED, data[1].first().state)
    }

    @Test
    fun testGetContentUnitTestLatestSuccess() = runBlockingTest {
        coEvery {
            appInteractor.getLatestNews()
        } returns DataTransferSuccess(listOf(createLatestUIArticle()))
        coEvery {
            appInteractor.getSavedNews()
        } returns NetworkUnavailableNotCached

        val data = homePresenter.getContent()

        assertEquals(2, data?.size)
        assertEquals(1, data!![0].size)
        assertEquals(0, data[1].size)
        assertEquals(ArticleState.LATEST, data[0].first().state)
    }

    @Test
    fun testGetContentUnitTestSavedSuccess() = runBlockingTest {
        coEvery {
            appInteractor.getLatestNews()
        } returns NetworkUnavailableNotCached
        coEvery {
            appInteractor.getSavedNews()
        } returns DataTransferSuccess(listOf(createSavedUIArticle()))

        val data = homePresenter.getContent()

        assertEquals(2, data?.size)
        assertEquals(0, data!![0].size)
        assertEquals(1, data[1].size)
        assertEquals(ArticleState.SAVED, data[1].first().state)
    }

    @Test
    fun testGetContentUnitTestFailure() = runBlockingTest {
        coEvery {
            appInteractor.getLatestNews()
        } returns NetworkUnavailableNotCached
        coEvery {
            appInteractor.getSavedNews()
        } returns NetworkUnavailableNotCached

        val data = homePresenter.getContent()

        assertEquals(null, data)
    }

    @Test
    fun testSaveArticleSuccess() = runBlockingTest {
        coEvery {
            appInteractor.saveArticle(createLatestUIArticle())
        } returns DataTransferSuccess(true)

        val status = homePresenter.saveArticle(createLatestUIArticle())
        assertEquals(true, status)
    }

    @Test
    fun testSaveArticleFailure() = runBlockingTest {
        coEvery {
            appInteractor.saveArticle(createLatestUIArticle())
        } returns NetworkUnavailableNotCached

        val status = homePresenter.saveArticle(createLatestUIArticle())
        assertEquals(null, status)
    }

    @Test
    fun testDeleteArticleSuccess() = runBlockingTest {
        coEvery {
            appInteractor.deleteArticle(createSavedUIArticle().uri)
        } returns DataTransferSuccess(true)

        val status = homePresenter.deleteArticle(createSavedUIArticle().uri)
        assertEquals(true, status)
    }

    @Test
    fun testDeleteArticleFailure() = runBlockingTest {
        coEvery {
            appInteractor.deleteArticle(createSavedUIArticle().uri)
        } returns NetworkUnavailableNotCached

        val status = homePresenter.deleteArticle(createSavedUIArticle().uri)
        assertEquals(null, status)
    }
}
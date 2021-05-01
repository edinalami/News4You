package hu.bme.aut.news4you.ui.home

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.news4you.interactor.AppInteractor
import hu.bme.aut.news4you.ui.model.UIArticle
import hu.bme.aut.news4you.util.network.SomeResult
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val appInteractor: AppInteractor
) {

    suspend fun getContent(): List<List<UIArticle>>? = withIOContext {
        when (val latest = appInteractor.getLatestNews()) {
            is SomeResult -> {
                when (val saved = appInteractor.getSavedNews()) {
                    is SomeResult -> {
                        val mutableLatestArticles = latest.result.toMutableList()
                        for (article in saved.result) {
                            val articleAlreadySaved =
                                mutableLatestArticles.find { it.uri == article.uri }
                            if (articleAlreadySaved != null) {
                                mutableLatestArticles.remove(articleAlreadySaved)
                            }
                        }
                        listOf(mutableLatestArticles, saved.result)
                    }
                    else -> listOf(latest.result, listOf())
                }
            }
            else -> {
                when (val saved = appInteractor.getSavedNews()) {
                    is SomeResult -> listOf(listOf(), saved.result)
                    else -> null
                }
            }
        }
    }

    suspend fun saveArticle(uiArticle: UIArticle): Boolean? = withIOContext {
        when (val response = appInteractor.saveArticle(uiArticle)) {
            is SomeResult -> {
                response.result
            }
            else -> {
                null
            }
        }
    }

    suspend fun deleteArticle(uri: String): Boolean? = withIOContext {
        when (val response = appInteractor.deleteArticle(uri)) {
            is SomeResult -> {
                response.result
            }
            else -> {
                null
            }
        }
    }

}
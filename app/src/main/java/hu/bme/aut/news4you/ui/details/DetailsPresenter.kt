package hu.bme.aut.news4you.ui.details

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.news4you.interactor.AppInteractor
import hu.bme.aut.news4you.ui.model.UIArticle
import hu.bme.aut.news4you.util.network.SomeResult
import javax.inject.Inject

class DetailsPresenter @Inject constructor(private val appInteractor: AppInteractor) {

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
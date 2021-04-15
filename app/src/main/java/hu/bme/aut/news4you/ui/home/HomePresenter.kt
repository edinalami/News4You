package hu.bme.aut.news4you.ui.home

import co.zsmb.rainbowcake.withIOContext
import hu.bme.aut.news4you.interactor.AppInteractor
import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.util.network.SomeResult
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val appInteractor: AppInteractor
) {

    suspend fun getLatestNews(): List<DomainArticle>? = withIOContext {
        when (val response = appInteractor.getLatestNews()) {
            is SomeResult -> response.result
            else -> null
        }
    }

}
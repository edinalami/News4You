package hu.bme.aut.news4you.ui.home

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import hu.bme.aut.news4you.ui.model.UIArticle
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homePresenter: HomePresenter
) : RainbowCakeViewModel<HomeViewState>(Initial) {

    fun load() = execute {
        viewState = Loading

        val data = homePresenter.getContent()

        viewState = if (data != null) {
            HomeReady(data)
        } else {
            Error
        }
    }

    fun save(uiArticle: UIArticle) = execute {
        viewState = Loading

        val status = homePresenter.saveArticle(uiArticle)

        viewState = if (status != null) {
            Saved
        } else {
            Error
        }
    }

    fun delete(uri: String) = execute {
        viewState = Loading

        val status = homePresenter.deleteArticle(uri)

        viewState = if (status != null) {
            Deleted
        } else {
            Error
        }
    }

    fun init() = execute {
        viewState = Initial
    }

}
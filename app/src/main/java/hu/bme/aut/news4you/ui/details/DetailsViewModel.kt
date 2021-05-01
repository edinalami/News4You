package hu.bme.aut.news4you.ui.details

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import hu.bme.aut.news4you.ui.model.UIArticle
import javax.inject.Inject

class DetailsViewModel @Inject constructor(
    private val detailsPresenter: DetailsPresenter
) : RainbowCakeViewModel<DetailsViewState>(Initial) {

    fun save(uiArticle: UIArticle) = execute {
        viewState = Loading

        val status = detailsPresenter.saveArticle(uiArticle)

        viewState = if (status != null) {
            Saved
        } else {
            Error
        }
    }

    fun delete(uri: String) = execute {
        viewState = Loading

        val status = detailsPresenter.deleteArticle(uri)

        viewState = if (status != null) {
            Deleted
        } else {
            Error
        }
    }

}
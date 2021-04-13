package hu.bme.aut.news4you.ui.about

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class AboutViewModel @Inject constructor(
    private val aboutPresenter: AboutPresenter
) : RainbowCakeViewModel<AboutViewState>(Loading) {

    fun load() = execute {
        viewState = AboutReady(aboutPresenter.getData())
    }

}
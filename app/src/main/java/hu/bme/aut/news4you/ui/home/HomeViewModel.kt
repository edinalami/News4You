package hu.bme.aut.news4you.ui.home

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homePresenter: HomePresenter
) : RainbowCakeViewModel<HomeViewState>(Loading) {

    fun load() = execute {
        viewState = HomeReady(homePresenter.getData())
    }

}
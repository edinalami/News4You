package hu.bme.aut.news4you.ui.home

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val homePresenter: HomePresenter
) : RainbowCakeViewModel<HomeViewState>(Initial) {

    fun load() = execute {
        viewState = Loading

        val data = homePresenter.getLatestNews()

        viewState = if (data != null) {
            HomeReady(data)
        } else {
            Error
        }
    }

}
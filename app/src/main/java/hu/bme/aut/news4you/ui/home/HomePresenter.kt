package hu.bme.aut.news4you.ui.home

import co.zsmb.rainbowcake.withIOContext
import javax.inject.Inject

class HomePresenter @Inject constructor() {

    suspend fun getData(): String = withIOContext {
        ""
    }

}
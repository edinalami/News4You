package hu.bme.aut.news4you.ui.home

import android.os.Bundle
import android.view.View
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.extensions.exhaustive
import hu.bme.aut.news4you.R
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : RainbowCakeFragment<HomeViewState, HomeViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO Setup views
    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: HomeViewState) {
        when (viewState) {
            is Initial -> Timber.d("Initial")
            is Loading -> Timber.d("Loading")
            is HomeReady -> {
                experiment.text = viewState.data[0].multimediaUrl
            }
            is Error -> Timber.d("Error")
        }.exhaustive
    }

}
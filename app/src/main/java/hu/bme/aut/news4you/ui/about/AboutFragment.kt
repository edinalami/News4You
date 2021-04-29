package hu.bme.aut.news4you.ui.about

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import hu.bme.aut.news4you.R
import hu.bme.aut.news4you.util.glide.GlideApp
import kotlinx.android.synthetic.main.fragment_about.*


class AboutFragment : RainbowCakeFragment<AboutViewState, AboutViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_about

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.transform(CenterCrop(), RoundedCorners(32))
        GlideApp.with(requireContext())
            .load(ContextCompat.getDrawable(requireContext(), R.drawable.logo_news4you))
            .apply(requestOptions)
            .into(logo)

    }

    override fun onStart() {
        super.onStart()

        viewModel.load()
    }

    override fun render(viewState: AboutViewState) {
        // TODO Render state
    }

}
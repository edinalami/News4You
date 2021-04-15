package hu.bme.aut.news4you.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.extensions.exhaustive
import hu.bme.aut.news4you.R
import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.ui.home.viewpager.HomePagerAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import timber.log.Timber

class HomeFragment : RainbowCakeFragment<HomeViewState, HomeViewModel>(),
    HomePagerAdapter.Listener {

    private lateinit var homePagerAdapter: HomePagerAdapter

    private var latest: MutableList<DomainArticle> = mutableListOf()
    private var saved: MutableList<DomainArticle> = mutableListOf()

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tab_layout.setupWithViewPager(pager)

        homePagerAdapter =
            HomePagerAdapter(
                childFragmentManager,
                latest,
                saved
            )
        homePagerAdapter.listener = this
        pager.adapter = homePagerAdapter

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
                Timber.d("HomeReady")
                latest.let {
                    it.clear()
                    it.addAll(viewState.data[0])
                }

                saved.let {
                    it.clear()
                    it.addAll(viewState.data[1])
                }

                homePagerAdapter.getItem(0).adapter.let {
                    it.submitList(latest)
                    it.notifyDataSetChanged()
                }
                homePagerAdapter.getItem(1).adapter.let {
                    it.submitList(saved)
                    it.notifyDataSetChanged()
                }
            }
            is Saved -> {
                Timber.d("Saved")
                viewModel.load()
            }
            is Deleted -> {
                Timber.d("Deleted")
                viewModel.load()
            }
            is Error -> Timber.d("Error")
        }.exhaustive
    }

    override fun onArticleClicked(article: DomainArticle) {
        Toast.makeText(context, "${article.title} CLICKED", Toast.LENGTH_SHORT).show()
    }

    override fun onSaveClicked(article: DomainArticle) {
        Toast.makeText(context, "${article.title} SAVED", Toast.LENGTH_SHORT).show()
        viewModel.save(article)
    }

    override fun onDeleteClicked(article: DomainArticle) {
        Toast.makeText(context, "${article.title} DELETED", Toast.LENGTH_SHORT).show()
        viewModel.delete(article.uri)
    }

}
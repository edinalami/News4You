package hu.bme.aut.news4you.ui.home

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import co.zsmb.rainbowcake.extensions.exhaustive
import co.zsmb.rainbowcake.navigation.navigator
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import hu.bme.aut.news4you.R
import hu.bme.aut.news4you.ui.about.AboutFragment
import hu.bme.aut.news4you.ui.details.DetailsFragment
import hu.bme.aut.news4you.ui.home.viewpager.HomePagerAdapter
import hu.bme.aut.news4you.ui.model.UIArticle
import hu.bme.aut.news4you.util.messaging.ArticleClickedEvent
import hu.bme.aut.news4you.util.messaging.ArticleDeletedEvent
import hu.bme.aut.news4you.util.messaging.ArticleSavedEvent
import kotlinx.android.synthetic.main.fragment_home.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber


class HomeFragment : RainbowCakeFragment<HomeViewState, HomeViewModel>() {

    private lateinit var firebaseAnalytics: FirebaseAnalytics

    private lateinit var homePagerAdapter: HomePagerAdapter

    private var latest: MutableList<UIArticle> = mutableListOf()
    private var saved: MutableList<UIArticle> = mutableListOf()

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        firebaseAnalytics = Firebase.analytics

        homePagerAdapter =
            HomePagerAdapter(
                childFragmentManager,
                latest,
                saved
            )

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tab_layout.setupWithViewPager(pager)
        pager.adapter = homePagerAdapter

        fabCrash.setOnClickListener {
            throw RuntimeException("News4You crashed")
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onResume() {
        super.onResume()
        viewModel.load()
    }

    override fun onStop() {
        EventBus.getDefault().unregister(this)
        super.onStop()
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

                viewModel.init()
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onArticleClickedEvent(event: ArticleClickedEvent) {
        val detailsFragment = DetailsFragment()
        val bundle = Bundle()
        bundle.putSerializable("article", event.article)
        detailsFragment.arguments = bundle

        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, event.article.uri)
            param(FirebaseAnalytics.Param.ITEM_NAME, event.article.title)
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "article")
        }

        navigator?.add(detailsFragment)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onArticleSavedEvent(event: ArticleSavedEvent) {
        Toast.makeText(context, "${event.article.title} SAVED", Toast.LENGTH_SHORT).show()
        viewModel.save(event.article)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onArticleDeletedEvent(event: ArticleDeletedEvent) {
        Toast.makeText(context, "${event.article.title} DELETED", Toast.LENGTH_SHORT).show()
        viewModel.delete(event.article.uri)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.AboutAction -> {
                navigator?.add(AboutFragment())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
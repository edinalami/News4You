package hu.bme.aut.news4you.ui.home.viewpager

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter.Companion.LATEST
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter.Companion.SAVED

class HomePagerAdapter(
    fm: FragmentManager,
    latest: List<DomainArticle>,
    saved: List<DomainArticle>
) : FragmentStatePagerAdapter(fm), ArticleListFragment.Listener {

    var listener: Listener? = null

    private var fragments: MutableList<ArticleListFragment> = mutableListOf()

    init {
        val fragmentLatest = ArticleListFragment(LATEST, latest)
        fragmentLatest.listener = this
        fragments.add(fragmentLatest)

        val fragmentSaved = ArticleListFragment(SAVED, saved)
        fragmentSaved.listener = this
        fragments.add(fragmentSaved)
    }

    override fun getCount(): Int = 2

    override fun getItem(position: Int): ArticleListFragment {
        return fragments[position]
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            LATEST -> {
                "LATEST"
            }
            SAVED -> {
                "SAVED"
            }
            else -> {
                ""
            }
        }
    }

    override fun onArticleClicked(article: DomainArticle) {
        listener?.onArticleClicked(article)
    }

    override fun onSaveClicked(article: DomainArticle) {
        listener?.onSaveClicked(article)
    }

    override fun onDeleteClicked(article: DomainArticle) {
        listener?.onDeleteClicked(article)
    }

    interface Listener {
        fun onArticleClicked(article: DomainArticle)
        fun onSaveClicked(article: DomainArticle)
        fun onDeleteClicked(article: DomainArticle)
    }

}
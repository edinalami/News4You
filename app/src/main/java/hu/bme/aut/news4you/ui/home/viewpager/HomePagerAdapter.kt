package hu.bme.aut.news4you.ui.home.viewpager

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter.Companion.LATEST
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter.Companion.SAVED
import hu.bme.aut.news4you.ui.model.UIArticle

class HomePagerAdapter(
    fm: FragmentManager,
    latest: List<UIArticle>,
    saved: List<UIArticle>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragments: MutableList<ArticleListFragment> = mutableListOf()

    init {
        val fragmentLatest = ArticleListFragment(LATEST, latest)
        fragments.add(fragmentLatest)

        val fragmentSaved = ArticleListFragment(SAVED, saved)
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

}
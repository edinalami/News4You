package hu.bme.aut.news4you.ui.home.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.news4you.R
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter.Companion.LATEST
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter.Companion.SAVED
import hu.bme.aut.news4you.ui.model.UIArticle
import kotlinx.android.synthetic.main.fragment_article_list.*

class ArticleListFragment() : Fragment() {

    private var type: Int = -1
    private val articles: MutableList<UIArticle> = mutableListOf()

    var adapter: ArticleAdapter = ArticleAdapter(LATEST)

    constructor(type: Int, articles: List<UIArticle>) : this() {
        this.type = type
        this.articles.addAll(articles)

        adapter = when (type) {
            LATEST -> {
                ArticleAdapter(LATEST)
            }
            SAVED -> {
                ArticleAdapter(SAVED)
            }
            else -> {
                ArticleAdapter(LATEST)
            }
        }

        adapter.submitList(articles)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        listArticles.layoutManager = LinearLayoutManager(requireContext())
        listArticles.adapter = adapter
    }

}
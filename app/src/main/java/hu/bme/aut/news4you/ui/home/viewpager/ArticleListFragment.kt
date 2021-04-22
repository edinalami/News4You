package hu.bme.aut.news4you.ui.home.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.news4you.R
import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter.Companion.LATEST
import hu.bme.aut.news4you.ui.home.adapter.ArticleAdapter.Companion.SAVED
import kotlinx.android.synthetic.main.fragment_article_list.*

class ArticleListFragment() :
    Fragment(), ArticleAdapter.Listener {

    private var type: Int = -1
    private val articles: MutableList<DomainArticle> = mutableListOf()

    var listener: Listener? = null

    lateinit var adapter: ArticleAdapter

    constructor(type: Int, articles: List<DomainArticle>) : this() {
        this.type = type
        this.articles.addAll(articles)
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

        adapter.listener = this
        adapter.submitList(articles)
        listArticles.layoutManager = LinearLayoutManager(context)
        listArticles.adapter = adapter

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
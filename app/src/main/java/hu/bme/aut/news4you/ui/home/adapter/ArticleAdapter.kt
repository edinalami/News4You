package hu.bme.aut.news4you.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.news4you.R
import hu.bme.aut.news4you.interactor.model.DomainArticle
import kotlinx.android.synthetic.main.article_row_latest.view.*
import kotlinx.android.synthetic.main.article_row_latest.view.tvPosition
import kotlinx.android.synthetic.main.article_row_latest.view.tvTitle
import kotlinx.android.synthetic.main.article_row_saved.view.*

class ArticleAdapter(private val type: Int) :
    ListAdapter<DomainArticle, ArticleAdapter.ArticleViewHolder>(ArticleComparator) {

    var listener: Listener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return when (type) {
            LATEST -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.article_row_latest, parent, false)
                ArticleViewHolder(view)
            }
            SAVED -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.article_row_saved, parent, false)
                ArticleViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.article_row_latest, parent, false)
                ArticleViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = getItem(position)

        holder.article = article

        holder.tvPos.text = position.toString()
        holder.tvTitle.text = article.title
    }

    inner class ArticleViewHolder(articleView: View) : RecyclerView.ViewHolder(articleView) {
        var article: DomainArticle? = null

        val tvPos: TextView = articleView.tvPosition
        val tvTitle: TextView = articleView.tvTitle

        private val imgSave: ImageView? = articleView.imgSave
        private val imgDelete: ImageView? = articleView.imgDelete

        init {
            articleView.setOnClickListener {
                article?.let { listener?.onArticleClicked(it) }
            }
            imgSave?.setOnClickListener {
                article?.let { listener?.onSaveClicked(it) }
            }
            imgDelete?.setOnClickListener {
                article?.let { listener?.onDeleteClicked(it) }
            }
        }
    }

    interface Listener {
        fun onArticleClicked(article: DomainArticle)
        fun onSaveClicked(article: DomainArticle)
        fun onDeleteClicked(article: DomainArticle)
    }

    companion object {
        const val LATEST = 0
        const val SAVED = 1
    }

}
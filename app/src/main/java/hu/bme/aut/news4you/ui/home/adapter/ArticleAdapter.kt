package hu.bme.aut.news4you.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.news4you.R
import hu.bme.aut.news4you.ui.home.model.UIArticle
import hu.bme.aut.news4you.util.messaging.ArticleClickedEvent
import hu.bme.aut.news4you.util.messaging.ArticleDeletedEvent
import hu.bme.aut.news4you.util.messaging.ArticleSavedEvent
import kotlinx.android.synthetic.main.article_row_latest.view.*
import kotlinx.android.synthetic.main.article_row_latest.view.tvDate
import kotlinx.android.synthetic.main.article_row_latest.view.tvSection
import kotlinx.android.synthetic.main.article_row_latest.view.tvTitle
import kotlinx.android.synthetic.main.article_row_saved.view.*
import org.greenrobot.eventbus.EventBus

class ArticleAdapter(private val type: Int) :
    ListAdapter<UIArticle, ArticleAdapter.ArticleViewHolder>(ArticleComparator) {

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

        holder.tvTitle.text = article.title
        holder.tvSection.text = article.section
        holder.tvDate.text = article.publishedDate.substringBefore('T')
    }

    inner class ArticleViewHolder(articleView: View) : RecyclerView.ViewHolder(articleView) {
        var article: UIArticle? = null

        val tvSection: TextView = articleView.tvSection
        val tvDate: TextView = articleView.tvDate
        val tvTitle: TextView = articleView.tvTitle

        private val imgSave: ImageView? = articleView.imgSave
        private val imgDelete: ImageView? = articleView.imgDelete

        init {
            articleView.setOnClickListener {
                EventBus.getDefault().post(ArticleClickedEvent(article!!))
            }

            imgSave?.setOnClickListener {
                EventBus.getDefault().post(ArticleSavedEvent(article!!))
            }

            imgDelete?.setOnClickListener {
                EventBus.getDefault().post(ArticleDeletedEvent(article!!))
            }
        }
    }

    companion object {
        const val LATEST = 0
        const val SAVED = 1
    }

}
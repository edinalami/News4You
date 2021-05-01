package hu.bme.aut.news4you.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.news4you.ui.model.UIArticle

object ArticleComparator : DiffUtil.ItemCallback<UIArticle>() {

    override fun areItemsTheSame(oldItem: UIArticle, newItem: UIArticle): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: UIArticle, newItem: UIArticle): Boolean {
        return oldItem == newItem
    }

}
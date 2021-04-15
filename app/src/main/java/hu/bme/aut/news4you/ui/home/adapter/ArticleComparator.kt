package hu.bme.aut.news4you.ui.home.adapter

import androidx.recyclerview.widget.DiffUtil
import hu.bme.aut.news4you.interactor.model.DomainArticle

object ArticleComparator : DiffUtil.ItemCallback<DomainArticle>() {

    override fun areItemsTheSame(oldItem: DomainArticle, newItem: DomainArticle): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: DomainArticle, newItem: DomainArticle): Boolean {
        return oldItem == newItem
    }

}
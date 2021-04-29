package hu.bme.aut.news4you.ui.home.model

import hu.bme.aut.news4you.interactor.model.DomainArticle
import java.io.Serializable

class UIArticle(
    uri: String,
    section: String,
    title: String,
    abstract: String,
    url: String,
    publishedDate: String,
    multimediaUrl: String,
    var state: ArticleState
) : DomainArticle(uri, section, title, abstract, url, publishedDate, multimediaUrl), Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }
        val article = other as DomainArticle
        return section == article.section &&
                title == article.title &&
                abstract == article.abstract &&
                url == article.url &&
                uri == article.uri &&
                publishedDate == article.publishedDate &&
                multimediaUrl == article.multimediaUrl &&
                state == state
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + state.hashCode()
        return result
    }

}
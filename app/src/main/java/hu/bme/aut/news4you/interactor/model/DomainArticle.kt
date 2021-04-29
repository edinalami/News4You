package hu.bme.aut.news4you.interactor.model

open class DomainArticle(
    val uri: String,
    val section: String,
    val title: String,
    val abstract: String,
    val url: String,
    val publishedDate: String,
    val multimediaUrl: String
) {
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
                multimediaUrl == article.multimediaUrl
    }

    override fun hashCode(): Int {
        var result = uri.hashCode()
        result = 31 * result + section.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + abstract.hashCode()
        result = 31 * result + url.hashCode()
        result = 31 * result + publishedDate.hashCode()
        result = 31 * result + multimediaUrl.hashCode()
        return result
    }
}
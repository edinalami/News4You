package hu.bme.aut.news4you.interactor

import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.ui.model.ArticleState
import hu.bme.aut.news4you.ui.model.UIArticle

fun DomainArticle.toUIArticle(state: ArticleState): UIArticle {
    return UIArticle(
        uri, section, title, abstract, url, publishedDate, multimediaUrl, state
    )
}

fun UIArticle.toDomainArticle(): DomainArticle {
    return DomainArticle(
        uri, section, title, abstract, url, publishedDate, multimediaUrl
    )
}
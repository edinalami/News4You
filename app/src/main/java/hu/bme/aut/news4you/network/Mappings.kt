package hu.bme.aut.news4you.network

import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.interactor.model.DomainUser
import hu.bme.aut.news4you.network.model.Article
import hu.bme.aut.news4you.network.model.ArticleMultimedia
import hu.bme.aut.news4you.network.model.User

fun Article.toDomainArticle(): DomainArticle {
    return DomainArticle(
        uri = uri,
        section = section,
        title = title,
        abstract = abstract,
        url = url,
        publishedDate = publishedDate,
        multimediaUrl = multimedia.first().url
    )
}

fun DomainArticle.toArticle(): Article {
    val multimedia = ArticleMultimedia()
    multimedia.url = multimediaUrl

    val article = Article()

    article.uri = uri
    article.section = section
    article.title = title
    article.abstract = abstract
    article.url = url
    article.publishedDate = publishedDate
    article.multimedia = listOf(multimedia)

    return article
}

fun User.toDomainUser(): DomainUser {
    return DomainUser(
        id = id,
        username = username
    )
}

fun DomainUser.toUser(): User {
    val user = User()

    user.id = id ?: 0
    user.username = username

    return user
}


package hu.bme.aut.news4you.mockAndroidTest

import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.interactor.model.DomainUser

fun createDomainArticle(
    uri: String = "",
    section: String = "",
    title: String = "",
    abstract: String = "",
    url: String = "",
    publishedDate: String = "",
    multimediaUrl: String = ""
) = DomainArticle(uri, section, title, abstract, url, publishedDate, multimediaUrl)

fun createDomainUser(
    id: Long = 0,
    username: String = ""
) = DomainUser(id, username)
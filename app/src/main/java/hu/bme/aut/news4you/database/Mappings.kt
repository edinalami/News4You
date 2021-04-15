package hu.bme.aut.news4you.database

import hu.bme.aut.news4you.database.model.RoomArticle
import hu.bme.aut.news4you.database.model.RoomUser
import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.interactor.model.DomainUser

fun RoomArticle.toDomainArticle(): DomainArticle {
    return DomainArticle(
        uri = id,
        section = section,
        title = title,
        abstract = abstract,
        url = url,
        publishedDate = publishedDate,
        multimediaUrl = multimediaUrl
    )
}

fun DomainArticle.toRoomArticle(latest: Boolean = false, saved: Boolean = false): RoomArticle {
    return RoomArticle(
        id = uri,
        section = section,
        title = title,
        abstract = abstract,
        url = url,
        publishedDate = publishedDate,
        multimediaUrl = multimediaUrl,
        latest = latest,
        saved = saved
    )
}

fun RoomUser.toDomainUser(): DomainUser {
    return DomainUser(
        id = id ?: 0,
        username = username
    )
}

fun DomainUser.toRoomUser(): RoomUser {
    return RoomUser(
        id = id,
        username = username
    )
}
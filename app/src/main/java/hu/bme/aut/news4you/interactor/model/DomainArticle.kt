package hu.bme.aut.news4you.interactor.model

class DomainArticle(
    val uri: String,
    val section: String,
    val title: String,
    val abstract: String,
    val url: String,
    val publishedDate: String,
    val multimediaUrl: String
)
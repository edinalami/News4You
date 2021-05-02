package hu.bme.aut.news4you.testMock

import hu.bme.aut.news4you.database.model.RoomArticle
import hu.bme.aut.news4you.ui.model.ArticleState
import hu.bme.aut.news4you.ui.model.UIArticle

fun createLatestUIArticle(
    uri: String = "nyt://article/e876366a-6018-5318-ab0d-f9051fcd6da1",
    section: String = "well",
    title: String = "Why You Shouldn’t Skip Your Second Covid Shot",
    abstract: String = "Your second dose of vaccine gives you more protection than you might think. Here’s why you should still get it, even if it’s later than planned.",
    url: String = "\"https://www.nytimes.com/2021/04/29/well/live/skipping-second-dose-coronavirus-vaccine.html",
    publishedDate: String = "2021-04-29T15:58:29-04:00",
    multimediaUrl: String = "\"https://static01.nyt.com/images/2021/04/29/well/well-shot/merlin_187047948_716b622d-06ad-4081-9e41-c7f15223d426-superJumbo.jpg"
) = UIArticle(uri, section, title, abstract, url, publishedDate, multimediaUrl, ArticleState.LATEST)

fun createSavedUIArticle(
    uri: String = "nyt://article/dbe7be50-d272-5d5b-a0f5-df08886d1ed7",
    section: String = "health",
    title: String = "Faith, Freedom, Fear: Rural America’s Covid Vaccine Skeptics",
    abstract: String = "Resistance is widespread in white, Republican communities like this one in Appalachia. But it’s far more complicated than just a partisan divide.",
    url: String = "\"https://www.nytimes.com/2021/04/30/health/covid-vaccine-hesitancy-white-republican.html",
    publishedDate: String = "2021-04-30T12:19:42-04:00",
    multimediaUrl: String = "\"https://static01.nyt.com/images/2021/04/21/science/00VIRUS-VAX-TENN-gospelhouse/merlin_186278148_b4887446-19fb-4d06-b529-7a83bcbb55a3-superJumbo.jpg"
) = UIArticle(uri, section, title, abstract, url, publishedDate, multimediaUrl, ArticleState.SAVED)

fun createCachedRoomArticle(
    uri: String = "nyt://article/e876366a-6018-5318-ab0d-f9051fcd6da1",
    section: String = "well",
    title: String = "Why You Shouldn’t Skip Your Second Covid Shot",
    abstract: String = "Your second dose of vaccine gives you more protection than you might think. Here’s why you should still get it, even if it’s later than planned.",
    url: String = "\"https://www.nytimes.com/2021/04/29/well/live/skipping-second-dose-coronavirus-vaccine.html",
    publishedDate: String = "2021-04-29T15:58:29-04:00",
    multimediaUrl: String = "\"https://static01.nyt.com/images/2021/04/29/well/well-shot/merlin_187047948_716b622d-06ad-4081-9e41-c7f15223d426-superJumbo.jpg"
) = RoomArticle(uri, section, title, abstract, url, publishedDate, multimediaUrl)

fun createSavedRoomArticle(
    uri: String = "nyt://article/dbe7be50-d272-5d5b-a0f5-df08886d1ed7",
    section: String = "health",
    title: String = "Faith, Freedom, Fear: Rural America’s Covid Vaccine Skeptics",
    abstract: String = "Resistance is widespread in white, Republican communities like this one in Appalachia. But it’s far more complicated than just a partisan divide.",
    url: String = "\"https://www.nytimes.com/2021/04/30/health/covid-vaccine-hesitancy-white-republican.html",
    publishedDate: String = "2021-04-30T12:19:42-04:00",
    multimediaUrl: String = "\"https://static01.nyt.com/images/2021/04/21/science/00VIRUS-VAX-TENN-gospelhouse/merlin_186278148_b4887446-19fb-4d06-b529-7a83bcbb55a3-superJumbo.jpg"
) = RoomArticle(uri, section, title, abstract, url, publishedDate, multimediaUrl)
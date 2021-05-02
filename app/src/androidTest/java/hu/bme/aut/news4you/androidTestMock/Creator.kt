package hu.bme.aut.news4you.androidTestMock

import hu.bme.aut.news4you.ui.model.ArticleState
import hu.bme.aut.news4you.ui.model.UIArticle

fun createLatestUIArticle(
    uri: String = "nyt://article/49043d6d-4093-590a-a17b-02b82eadc36c",
    section: String = "well",
    title: String = "Why You Shouldn’t Skip Your Second Covid Shot",
    abstract: String = "Your second dose of vaccine gives you more protection than you might think. Here’s why you should still get it, even if it’s later than planned.",
    url: String = "\"https://www.nytimes.com/2021/04/29/well/live/skipping-second-dose-coronavirus-vaccine.html",
    publishedDate: String = "2021-04-29T15:58:29-04:00",
    multimediaUrl: String = "\"https://static01.nyt.com/images/2021/04/29/well/well-shot/merlin_187047948_716b622d-06ad-4081-9e41-c7f15223d426-superJumbo.jpg"
) = UIArticle(uri, section, title, abstract, url, publishedDate, multimediaUrl, ArticleState.LATEST)
package hu.bme.aut.news4you.testMock.network

import hu.bme.aut.news4you.network.api.ReadingListApi
import hu.bme.aut.news4you.network.model.Article
import hu.bme.aut.news4you.network.model.ArticleMultimedia
import hu.bme.aut.news4you.network.model.ReadingListResponse

class MockReadingListApi : ReadingListApi {
    private val articles = mutableListOf<Article>()

    init {
        val article1 = Article()
        val article2 = Article()
        val article3 = Article()

        val articleMultimedia1 = ArticleMultimedia()
        val articleMultimedia2 = ArticleMultimedia()
        val articleMultimedia3 = ArticleMultimedia()

        articleMultimedia1.let {
            it.url =
                "https://static01.nyt.com/images/2021/04/16/us/16Indianapolis-shooting/merlin_186495996_1996714a-c2aa-4236-ad3a-536794b3b3aa-articleInline.jpg"
        }
        articleMultimedia2.let {
            it.url =
                "https://static01.nyt.com/images/2021/04/16/us/16indianapolis-shooting-briefing-what-we-know-about-the-victims-top/merlin_186508353_e7ac0c59-fbcc-4908-9009-ccc02d7bed02-articleInline.jpg"
        }
        articleMultimedia3.let {
            it.url =
                "https://static01.nyt.com/images/2021/04/17/world/17virus-vaccine-korea/merlin_186425910_d8898f00-1442-415b-85a6-b7e040108321-articleInline.jpg"
        }

        article1.let {
            it.uri = "nyt://article/b7fa8c97-b243-5ad3-82b2-41d3964636d3"
            it.section = "us"
            it.title = "What we know about the victims in the Indianapolis shooting."
            it.abstract = ""
            it.url =
                "https://www.nytimes.com/live/2021/04/16/us/indianapolis-fedex-shooting/what-we-know-about-the-victims-in-the-indianapolis-shooting"
            it.publishedDate = "2021-04-16T23:37:32-04:00"
            it.multimedia = listOf(articleMultimedia2)
        }

        article2.let {
            it.uri = "nyt://article/4b1efa4e-8c05-5f99-acef-725fe59385e9"
            it.section = "us"
            it.title = "The region’s Sikh community is devastated by the attack."
            it.abstract = "Many recalled a 2012 attack on a Sikh temple in Wisconsin."
            it.url =
                "https://www.nytimes.com/live/2021/04/16/us/indianapolis-fedex-shooting/the-regions-sikh-community-is-devastated-by-the-attack"
            it.publishedDate = "2021-04-16T23:39:15-04:00"
            it.multimedia = listOf(articleMultimedia3)
        }

        article3.let {
            it.uri = "nyt://article/fbaa08c4-2c82-553f-ae7c-300d91aaef85"
            it.section = "world"
            it.title = "These Countries Did Well With Covid. So Why Are They Slow on Vaccines?"
            it.abstract =
                "Japan, South Korea and Australia have inoculated tiny percentages of their populations. The delays risk unwinding their relative successes."
            it.url =
                "https://www.nytimes.com/2021/04/17/world/asia/japan-south-korea-australia-vaccines.html"
            it.publishedDate = "2021-04-17T05:00:19-04:00"
            it.multimedia = listOf(articleMultimedia3)
        }

        articles.add(article1)
        articles.add(article2)
        articles.add(article3)
    }

    override suspend fun deleteFromReadingList(articleUri: String) {
        for (article in articles) {
            if (article.uri == articleUri) {
                articles.remove(article)
            }
        }
    }

    override suspend fun saveToReadingList(body: Article) {
        articles.add(body)
    }

    override suspend fun getSavedArticles(): ReadingListResponse {
        val readingListResponse = ReadingListResponse()

        readingListResponse.results = articles

        return readingListResponse
    }
}
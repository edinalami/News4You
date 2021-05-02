package hu.bme.aut.news4you.testMock.network

import hu.bme.aut.news4you.network.api.NewsApi
import hu.bme.aut.news4you.network.model.Article
import hu.bme.aut.news4you.network.model.ArticleMultimedia
import hu.bme.aut.news4you.network.model.LatestNewsResponse

class MockNewsApi : NewsApi {
    private val articles = mutableListOf<Article>()

    init {
        val article1 = Article()
        val article2 = Article()
        val article3 = Article()
        val article4 = Article()
        val article5 = Article()

        val articleMultimedia1 = ArticleMultimedia()
        val articleMultimedia2 = ArticleMultimedia()
        val articleMultimedia3 = ArticleMultimedia()
        val articleMultimedia4 = ArticleMultimedia()
        val articleMultimedia5 = ArticleMultimedia()

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
                "https://static01.nyt.com/images/2021/04/16/us/16indianapolis-shooting-briefing-victims-top/16indianapolis-shooting-briefing-victims-top-articleInline.jpg"
        }
        articleMultimedia4.let {
            it.url =
                "https://static01.nyt.com/images/2021/04/17/us/17police-killings00/17police-killings-00-articleInline.jpg"
        }
        articleMultimedia5.let {
            it.url =
                "https://static01.nyt.com/images/2021/04/16/us/16pulled-over1/merlin_186472650_d9d3bdbc-0711-4d85-83a6-8347463a303a-articleInline.jpg"
        }

        article1.let {
            it.uri = "nyt://article/49043d6d-4093-590a-a17b-02b82eadc36c"
            it.section = "us"
            it.title =
                "In a Spasm of Violence, Indianapolis Faces the Country’s Latest Mass Shooting"
            it.abstract =
                "The country grappled with yet another bloody rampage, only weeks after back-to-back mass shootings last month in Atlanta and Boulder, Colo."
            it.url = "https://www.nytimes.com/2021/04/16/us/shooting-fedex.html"
            it.publishedDate = "2021-04-16T22:30:08-04:00"
            it.multimedia = listOf(articleMultimedia1)
        }

        article2.let {
            it.uri = "nyt://article/b7fa8c97-b243-5ad3-82b2-41d3964636d3"
            it.section = "us"
            it.title = "What we know about the victims in the Indianapolis shooting."
            it.abstract = ""
            it.url =
                "https://www.nytimes.com/live/2021/04/16/us/indianapolis-fedex-shooting/what-we-know-about-the-victims-in-the-indianapolis-shooting"
            it.publishedDate = "2021-04-16T23:37:32-04:00"
            it.multimedia = listOf(articleMultimedia2)
        }

        article3.let {
            it.uri = "nyt://article/4b1efa4e-8c05-5f99-acef-725fe59385e9"
            it.section = "us"
            it.title = "The region’s Sikh community is devastated by the attack."
            it.abstract = "Many recalled a 2012 attack on a Sikh temple in Wisconsin."
            it.url =
                "https://www.nytimes.com/live/2021/04/16/us/indianapolis-fedex-shooting/the-regions-sikh-community-is-devastated-by-the-attack"
            it.publishedDate = "2021-04-16T23:39:15-04:00"
            it.multimedia = listOf(articleMultimedia3)
        }

        article4.let {
            it.uri = "nyt://article/3c55a114-2d5a-5067-b480-51096b9ed9d8"
            it.section = "us"
            it.title = "Throughout Trial Over George Floyd’s Death, Killings by Police Mount"
            it.abstract =
                "Since testimony in Derek Chauvin’s trial began on March 29, more than three people a day have died at the hands of law enforcement."
            it.url = "https://www.nytimes.com/2021/04/17/us/police-shootings-killings.html"
            it.publishedDate = "2021-04-17T05:00:24-04:00"
            it.multimedia = listOf(articleMultimedia4)
        }

        article5.let {
            it.uri = "nyt://article/a9b86f6a-1281-5d2c-a215-e60651409fc3"
            it.section = "us"
            it.title = "Why Police Can Stop Motorists With Air Fresheners Hanging in Their Cars"
            it.abstract =
                "A majority of states have laws making it illegal to hang anything from a rearview mirror that obscures a driver’s view. But critics say the laws are often used as pretexts."
            it.url = "https://www.nytimes.com/2021/04/17/us/police-air-fresheners.html"
            it.publishedDate = "2021-04-17T03:00:08-04:00"
            it.multimedia = listOf(articleMultimedia5)
        }

        articles.add(article1)
        articles.add(article2)
        articles.add(article3)
        articles.add(article4)
        articles.add(article5)
    }

    override suspend fun getLatestNews(): LatestNewsResponse {
        val latestNewsResponse = LatestNewsResponse()

        latestNewsResponse.results = articles

        return latestNewsResponse
    }
}
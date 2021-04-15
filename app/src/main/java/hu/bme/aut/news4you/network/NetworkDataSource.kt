package hu.bme.aut.news4you.network

import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.interactor.model.DomainUser
import hu.bme.aut.news4you.network.api.NewsApi
import hu.bme.aut.news4you.network.api.ReadingListApi
import hu.bme.aut.news4you.network.api.UserApi
import hu.bme.aut.news4you.network.model.Article
import hu.bme.aut.news4you.util.network.NetworkResponse
import hu.bme.aut.news4you.util.network.executeNetworkCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSource @Inject constructor(
    private val newsApi: NewsApi,
    private val readingListApi: ReadingListApi,
    private val userApi: UserApi
) {
    suspend fun getLatestNews(): NetworkResponse<List<DomainArticle>> =
        executeNetworkCall {
            newsApi.getLatestNews().results.map(Article::toDomainArticle)
        }

    suspend fun getSavedArticles(): NetworkResponse<List<DomainArticle>> =
        executeNetworkCall {
            readingListApi.getSavedArticles().results.map(Article::toDomainArticle)
        }

    suspend fun saveArticle(domainArticle: DomainArticle) =
        executeNetworkCall {
            readingListApi.saveToReadingList(domainArticle.toArticle())
        }

    suspend fun deleteArticle(uri: String) =
        executeNetworkCall {
            readingListApi.deleteFromReadingList(uri)
        }

    suspend fun getUser(): NetworkResponse<DomainUser> = executeNetworkCall {
        userApi.getUser().toDomainUser()
    }

    suspend fun createUser(domainUser: DomainUser) = executeNetworkCall {
        userApi.createUser(domainUser.toUser())
    }

    suspend fun updateUser(domainUser: DomainUser) = executeNetworkCall {
        userApi.updateUser(domainUser.toUser())
    }
}

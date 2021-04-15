package hu.bme.aut.news4you.interactor

import hu.bme.aut.news4you.database.DiskDataSource
import hu.bme.aut.news4you.interactor.model.DomainArticle
import hu.bme.aut.news4you.interactor.model.DomainUser
import hu.bme.aut.news4you.network.NetworkDataSource
import hu.bme.aut.news4you.util.network.*
import javax.inject.Inject

class AppInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val diskDataSource: DiskDataSource
) {

    suspend fun getLatestNews(): DataTransferResponse<List<DomainArticle>> {
        return when (val response = networkDataSource.getLatestNews()) {
            is NetworkResult -> {
                diskDataSource.populateCachedNews(response.result)
                DataTransferSuccess(response.result)
            }
            else -> {
                val list = diskDataSource.getLatestNews()
                if (list.isNotEmpty()) {
                    NetworkUnavailableCached(list)
                } else {
                    NetworkUnavailableNotCached
                }
            }
        }
    }

    suspend fun getSavedNews(): DataTransferResponse<List<DomainArticle>> {
        return when (val response = networkDataSource.getSavedArticles()) {
            is NetworkResult -> {
                diskDataSource.populateSavedNews(response.result)
                DataTransferSuccess(response.result)
            }
            else -> {
                val list = diskDataSource.getSavedNews()
                if (list.isNotEmpty()) {
                    NetworkUnavailableCached(list)
                } else {
                    NetworkUnavailableNotCached
                }
            }
        }
    }

    suspend fun saveArticle(domainArticle: DomainArticle): DataTransferResponse<Boolean> {
        return when (networkDataSource.saveArticle(domainArticle)) {
            is NetworkResult -> {
                diskDataSource.saveArticle(domainArticle.uri)
                DataTransferSuccess(true)
            }
            else -> {
                NetworkUnavailableNotCached
            }
        }
    }

    suspend fun deleteArticle(uri: String): DataTransferResponse<Boolean> {
        return when (networkDataSource.deleteArticle(uri)) {
            is NetworkResult -> {
                diskDataSource.deleteArticle(uri)
                DataTransferSuccess(true)
            }
            else -> {
                NetworkUnavailableNotCached
            }
        }
    }

    suspend fun getUser(): DataTransferResponse<DomainUser> {
        return when (val response = networkDataSource.getUser()) {
            is NetworkResult -> {
                DataTransferSuccess(response.result)
            }
            else -> {
                val user = diskDataSource.getUser()
                if (user != null) {
                    NetworkUnavailableCached(user)
                } else {
                    NetworkUnavailableNotCached
                }
            }
        }
    }

    suspend fun createUser(domainUser: DomainUser): DataTransferResponse<Boolean> {
        return when (networkDataSource.createUser(domainUser)) {
            is NetworkResult -> {
                diskDataSource.insertUser(domainUser)
                DataTransferSuccess(true)
            }
            else -> {
                NetworkUnavailableNotCached
            }
        }
    }

    suspend fun updateUser(domainUser: DomainUser): DataTransferResponse<Boolean> {
        return when (networkDataSource.updateUser(domainUser)) {
            is NetworkResult -> {
                diskDataSource.updateUser(domainUser)
                DataTransferSuccess(true)
            }
            else -> {
                NetworkUnavailableNotCached
            }
        }
    }

}
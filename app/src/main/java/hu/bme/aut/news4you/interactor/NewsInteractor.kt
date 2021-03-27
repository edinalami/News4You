package hu.bme.aut.news4you.interactor

import hu.bme.aut.news4you.database.DiskDataSource
import hu.bme.aut.news4you.network.NetworkDataSource
import javax.inject.Inject

class NewsInteractor @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val diskDataSource: DiskDataSource
)
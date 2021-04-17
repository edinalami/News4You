package hu.bme.aut.news4you.ui.home

import hu.bme.aut.news4you.interactor.model.DomainArticle

sealed class HomeViewState

object Initial : HomeViewState()

object Loading : HomeViewState()

data class HomeReady(val data: List<List<DomainArticle>>) : HomeViewState()

object Saved : HomeViewState()

object Deleted : HomeViewState()

object Error : HomeViewState()
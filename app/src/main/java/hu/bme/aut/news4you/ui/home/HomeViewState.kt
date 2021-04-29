package hu.bme.aut.news4you.ui.home

import hu.bme.aut.news4you.ui.home.model.UIArticle

sealed class HomeViewState

object Initial : HomeViewState()

object Loading : HomeViewState()

data class HomeReady(val data: List<List<UIArticle>>) : HomeViewState()

object Saved : HomeViewState()

object Deleted : HomeViewState()

object Error : HomeViewState()
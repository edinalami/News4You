package hu.bme.aut.news4you.ui.home

sealed class HomeViewState

object Loading : HomeViewState()

data class HomeReady(val data: String = "") : HomeViewState()
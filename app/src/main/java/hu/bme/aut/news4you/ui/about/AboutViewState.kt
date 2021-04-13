package hu.bme.aut.news4you.ui.about

sealed class AboutViewState

object Loading : AboutViewState()

data class AboutReady(val data: String = "") : AboutViewState()
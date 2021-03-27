package hu.bme.aut.news4you.ui.details

sealed class DetailsViewState

object Loading : DetailsViewState()

data class DetailsReady(val data: String = "") : DetailsViewState()
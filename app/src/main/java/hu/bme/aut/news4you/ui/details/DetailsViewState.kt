package hu.bme.aut.news4you.ui.details

sealed class DetailsViewState

object Initial : DetailsViewState()

object Loading : DetailsViewState()

object Saved : DetailsViewState()

object Deleted : DetailsViewState()

object Error : DetailsViewState()
package hu.bme.aut.news4you.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import co.zsmb.rainbowcake.base.RainbowCakeFragment
import co.zsmb.rainbowcake.dagger.getViewModelFromFactory
import com.squareup.picasso.Picasso
import hu.bme.aut.news4you.R
import hu.bme.aut.news4you.ui.model.ArticleState
import hu.bme.aut.news4you.ui.model.UIArticle
import kotlinx.android.synthetic.main.fragment_details.*
import timber.log.Timber


class DetailsFragment : RainbowCakeFragment<DetailsViewState, DetailsViewModel>() {

    override fun provideViewModel() = getViewModelFromFactory()
    override fun getViewResource() = R.layout.fragment_details

    lateinit var article: UIArticle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle: Bundle? = arguments
        article = bundle?.getSerializable("article") as UIArticle

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get().load(article.multimediaUrl).fit().centerCrop().into(imgCover)

        tvArticleTitle.text = article.title
        tvArticleAbstract.text = article.abstract
        tvArticleSection.text = article.section
        tvArticlePublishDate.text = article.publishedDate.substringBefore('T')

        btnOpen.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(article.url)
            )
            startActivity(browserIntent)
        }

    }

    override fun render(viewState: DetailsViewState) {
        when (viewState) {
            is Initial -> Timber.d("Initial")
            is Loading -> Timber.d("Loading")
            is Saved -> {
                Timber.d("Saved")
                Toast.makeText(requireContext(), "${article.title} SAVED", Toast.LENGTH_SHORT)
                    .show()
                article.state = ArticleState.SAVED
                activity?.invalidateOptionsMenu()
            }
            is Deleted -> {
                Timber.d("Deleted")
                Toast.makeText(requireContext(), "${article.title} DELETED", Toast.LENGTH_SHORT)
                    .show()
                article.state = ArticleState.LATEST
                activity?.invalidateOptionsMenu()
            }
            is Error -> Timber.d("Error")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_details_menu, menu)
        when (article.state) {
            ArticleState.SAVED -> {
                menu.findItem(R.id.SaveAction).isVisible = false
            }
            ArticleState.LATEST -> {
                menu.findItem(R.id.DeleteAction).isVisible = false
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.SaveAction -> {
                viewModel.save(article)
                true
            }
            R.id.DeleteAction -> {
                viewModel.delete(article.uri)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
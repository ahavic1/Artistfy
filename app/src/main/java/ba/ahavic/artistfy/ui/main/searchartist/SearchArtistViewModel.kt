package ba.ahavic.artistfy.ui.main.searchartist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.data.artist.ArtistRepository
import ba.ahavic.artistfy.ui.base.AppError
import ba.ahavic.artistfy.ui.base.ReasonOfError
import ba.ahavic.artistfy.ui.base.viewmodel.BaseError
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import ba.ahavic.artistfy.ui.data.Artist
import javax.inject.Inject

class SearchArtistViewModel @Inject constructor(private val artistRepository: ArtistRepository) :
    BaseViewModel() {

    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>> = _artists

    val searchText = MutableLiveData<String>()

    override fun defaultErrorHandler(appError: AppError) {
        when (appError.reasonOfError) {
            ReasonOfError.InvalidParameters -> setError(InvalidSearchQuery)
            else -> super.defaultErrorHandler(appError)
        }
    }

    fun actionArtistSelected(artist: Artist) {
        navigate(SearchArtistFragmentDirections.actionSearchArtistFragmentToTopAlbumsFragment(artist.name))
    }

    fun actionSearchArtists() {
        launch {
            searchText.value?.let {
                isLoading(true)
                _artists.value = artistRepository.getArtists(it)
                isLoading(false)
            }
        }
    }
}

object InvalidSearchQuery : BaseError.FeatureError(
    R.string.all_error_default_title,
    R.string.search_artist_error_invalid_query
)
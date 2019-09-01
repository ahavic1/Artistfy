package ba.ahavic.artistfy.ui.main.searchartist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ba.ahavic.artistfy.data.artist.ArtistRepository
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import ba.ahavic.artistfy.ui.data.Artist
import javax.inject.Inject

class SearchArtistViewModel @Inject constructor(private val artistRepository: ArtistRepository) :
    BaseViewModel() {

    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>> = _artists

    val searchText = MutableLiveData<String>()

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
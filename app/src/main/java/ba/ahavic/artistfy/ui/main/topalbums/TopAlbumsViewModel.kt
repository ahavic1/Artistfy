package ba.ahavic.artistfy.ui.main.topalbums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ba.ahavic.artistfy.data.artist.ArtistRepository
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import ba.ahavic.artistfy.ui.data.Album
import javax.inject.Inject

class TopAlbumsViewModel @Inject constructor(
    private val repository: ArtistRepository
) : BaseViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> by lazy {
        val artistName = TopAlbumsFragmentArgs.fromBundle(arguments).artistName
        getTopAlbums(artistName)
        _albums
    }

    fun actionAlbumSelected(album: Album) {
        navigate(TopAlbumsFragmentDirections.actionTopAlbumsToAlbumDetails(album))
    }

    private fun getTopAlbums(artistName: String) {
        launch {
            isLoading(true)
            _albums.value = repository.getTopAlbums(artistName)
            isLoading(false)
        }
    }
}

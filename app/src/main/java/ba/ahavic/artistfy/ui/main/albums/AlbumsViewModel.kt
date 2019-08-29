package ba.ahavic.artistfy.ui.main.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.data.album.Album
import ba.ahavic.artistfy.data.album.AlbumRepository
import ba.ahavic.artistfy.ui.base.viewmodel.BaseError
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

class AlbumsViewModel @Inject constructor(albumRepository: AlbumRepository) : BaseViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    init {
        launch {
            isLoading(true)
            val albums = albumRepository.getAlbums()
            if (albums.isEmpty()) renderError(NoAlbumsError)
            else renderAlbums(albums)
            isLoading(false)
        }
    }

    fun actionAlbumSelected(album: Album) {

    }

    private fun renderError(error: NoAlbumsError) {
        setError(error)
    }

    private fun renderAlbums(albums: List<Album>) {
        _albums.value = albums
    }
}

object NoAlbumsError: BaseError.FeatureError(R.string.albums_no_albums_error, 0)
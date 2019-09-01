package ba.ahavic.artistfy.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ba.ahavic.artistfy.data.album.Album
import ba.ahavic.artistfy.data.album.AlbumRepository
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    BaseViewModel() {

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album> by lazy {
        getAlbumInfo(AlbumDetailsFragmentArgs.fromBundle(arguments).album)
        _album
    }

    fun actionSaveToMyAlbums() {
        launch {
            isLoading(true)
            _album.value = albumRepository.saveAlbum(album.value!!)
            isLoading(false)
        }
    }

    fun actionRemoveFromMyAlbums() {
        launch {
            isLoading(true)
            albumRepository.deleteAlbum(album.value!!)
            _album.value = album.value?.copy(
                cached = false
            )
            isLoading(false)
        }
    }

    private fun getAlbumInfo(album: Album) {
        launch {
            isLoading(true)
            _album.value = albumRepository.getAlbum(album)
            isLoading(false)
        }
    }
}

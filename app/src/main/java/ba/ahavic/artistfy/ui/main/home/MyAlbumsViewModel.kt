package ba.ahavic.artistfy.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.asMutableLiveData
import ba.ahavic.artistfy.data.album.Album
import ba.ahavic.artistfy.data.album.AlbumRepository
import ba.ahavic.artistfy.ui.base.viewmodel.BaseError
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

class MyAlbumsViewModel @Inject constructor(
    private val albumRepository: AlbumRepository
) : BaseViewModel() {

    private val _albums = MutableLiveData<List<Album>>()
    val albums: LiveData<List<Album>> = _albums

    val errorLayoutVisibility = false.asMutableLiveData()

    override fun onLifeCycleOwnerResume() {
        super.onLifeCycleOwnerResume()
        getMyAlbums()
    }

    fun actionAlbumSelected(album: Album) {
        navigate(MyAlbumsFragmentDirections.actionAlbumsToAlbumDetails(album))
    }

    fun goToSearchArtists() {
        navigate(MyAlbumsFragmentDirections.actionAlbumsToSearchArtist())
    }

    private fun getMyAlbums() {
        launch {
            isLoading(true)
            val albums = albumRepository.getAlbums()
            if (albums.isEmpty()) renderError(NoAlbumsError)
            else renderAlbums(albums)
            isLoading(false)
        }
    }

    private fun renderError(error: BaseError) {
        errorLayoutVisibility.value = true
        setError(error)
    }

    private fun renderAlbums(albums: List<Album>) {
        _albums.value = albums
    }
}

object NoAlbumsError: BaseError.FeatureError(R.string.albums_no_albums_error, 0)

package ba.ahavic.artistfy.ui.main.albumdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.data.album.AlbumRepository
import ba.ahavic.artistfy.ui.base.AppError
import ba.ahavic.artistfy.ui.base.ReasonOfError
import ba.ahavic.artistfy.ui.base.viewmodel.BaseError
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import ba.ahavic.artistfy.ui.data.Album
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    BaseViewModel() {

    private val _album = MutableLiveData<Album>()
    val album: LiveData<Album> by lazy {
        getAlbumInfo(AlbumDetailsFragmentArgs.fromBundle(arguments).album)
        _album
    }

    override fun defaultErrorHandler(appError: AppError) {
        when (appError.reasonOfError) {
            ReasonOfError.DatabaseSaveFailed -> setError(AlbumSaveFailed)
            else -> super.defaultErrorHandler(appError)
        }
    }

    fun actionSaveToMyAlbums() {
        launch {
            isLoading(true)
            if (albumRepository.saveAlbum(album.value!!)) {
                _album.value = _album.value?.copy(
                    cached = true
                )
            }
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
            _album.value = albumRepository.getAlbumInfo(album)
            isLoading(false)
        }
    }
}

object AlbumSaveFailed :
    BaseError.FeatureError(R.string.album_details_save_failed, R.string.album_details_save_failed)


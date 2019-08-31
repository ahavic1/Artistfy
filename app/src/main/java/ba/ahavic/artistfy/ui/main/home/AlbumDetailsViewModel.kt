package ba.ahavic.artistfy.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ba.ahavic.artistfy.asMutableLiveData
import ba.ahavic.artistfy.data.album.Album
import ba.ahavic.artistfy.data.album.AlbumRepository
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

class AlbumDetailsViewModel @Inject constructor(private val albumRepository: AlbumRepository) :
    BaseViewModel() {

    private val _album: MutableLiveData<Album> by lazy {
        AlbumDetailsFragmentArgs.fromBundle(arguments).album.asMutableLiveData()
    }
    val album: LiveData<Album> by lazy {
        _album
    }

    fun actionSaveToMyAlbums() {


    }

    fun actionRemoveFromMyAlbums() {

    }
}

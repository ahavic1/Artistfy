package ba.ahavic.artistfy.ui.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.data.album.Album
import ba.ahavic.artistfy.data.album.AlbumRepository
import ba.ahavic.artistfy.databinding.FragmentAlbumDetailsBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

class AlbumDetailsFragment : BaseBoundFragment<AlbumDetailsViewModel, FragmentAlbumDetailsBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_album_details
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<AlbumDetailsViewModel>
        get() = AlbumDetailsViewModel::class.java

    override fun bindToViewModel() {
    }
}

@Module
abstract class AlbumDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailsViewModel::class)
    abstract fun provideAlbumDetailsViewModel(viewModel: AlbumDetailsViewModel): ViewModel
}

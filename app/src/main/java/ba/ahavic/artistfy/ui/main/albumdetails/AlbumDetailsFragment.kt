package ba.ahavic.artistfy.ui.main.albumdetails

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.FragmentAlbumDetailsBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

class AlbumDetailsFragment : BaseBoundFragment<AlbumDetailsViewModel, FragmentAlbumDetailsBinding>() {

    private val tracksAdapter: TracksAdapter by lazy { TracksAdapter() }

    override val layoutId: Int
        get() = R.layout.fragment_album_details
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<AlbumDetailsViewModel>
        get() = AlbumDetailsViewModel::class.java

    override fun bindToViewModel() {
        viewDataBinding.recyclerTracks.adapter = tracksAdapter

        viewModel.album.observe(viewLifecycleOwner, Observer { album ->
            album.tracks?.let { tracksAdapter.setData(album.tracks) }
        })
    }
}

@Module
abstract class AlbumDetailsModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailsViewModel::class)
    abstract fun provideAlbumDetailsViewModel(viewModel: AlbumDetailsViewModel): ViewModel
}

package ba.ahavic.artistfy.ui.main.topalbums

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.FragmentTopAlbumsBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelKey
import ba.ahavic.artistfy.ui.main.home.AlbumsAdapter
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

class TopAlbumsFragment : BaseBoundFragment<TopAlbumsViewModel, FragmentTopAlbumsBinding>() {

    private val albumsAdapter by lazy {
        AlbumsAdapter(viewModel::actionAlbumSelected)
    }

    override val layoutId: Int
        get() = R.layout.fragment_top_albums
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<TopAlbumsViewModel>
        get() = TopAlbumsViewModel::class.java

    override fun bindToViewModel() {

        viewDataBinding.recyclerAlbums.run {
            setItemViewCacheSize(15)
            adapter = albumsAdapter
        }

        viewModel.albums.observe(viewLifecycleOwner, Observer {
            albumsAdapter.setData(it)
        })
    }
}

@Module
abstract class TopAlbumsModule {

    @Binds
    @IntoMap
    @ViewModelKey(TopAlbumsViewModel::class)
    abstract fun provideTopAlbumsViewModel(viewModel: TopAlbumsViewModel): ViewModel
}

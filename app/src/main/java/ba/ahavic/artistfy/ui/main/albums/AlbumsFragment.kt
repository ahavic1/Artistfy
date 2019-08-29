package ba.ahavic.artistfy.ui.main.albums

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.FragmentMainBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

class AlbumsFragment : BaseBoundFragment<AlbumsViewModel, FragmentMainBinding>() {

    private val albumsAdapter by lazy {
        AlbumsAdapter(viewModel::actionAlbumSelected)
    }

    override val layoutId: Int
        get() = R.layout.fragment_main
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<AlbumsViewModel>
        get() = AlbumsViewModel::class.java

    override fun bindToViewModel() {
        setupUI()

        viewModel.albums.observe(viewLifecycleOwner, Observer {
            albumsAdapter.setData(it)
        })
    }

    private fun setupUI() {
        viewDataBinding.recyclerAlbums.run {
            adapter = albumsAdapter
            setItemViewCacheSize(15)
        }
    }
}

@Module
abstract class MainFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(AlbumsViewModel::class)
    abstract fun bindMainFragmentViewModel(viewModel: AlbumsViewModel): ViewModel
}

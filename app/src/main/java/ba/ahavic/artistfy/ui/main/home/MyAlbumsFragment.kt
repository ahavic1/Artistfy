package ba.ahavic.artistfy.ui.main.home

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.FragmentMyAlbumsBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

class MyAlbumsFragment : BaseBoundFragment<MyAlbumsViewModel, FragmentMyAlbumsBinding>() {

    private val albumsAdapter by lazy {
        AlbumsAdapter(viewModel::actionAlbumSelected)
    }

    override val layoutId: Int
        get() = R.layout.fragment_my_albums
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<MyAlbumsViewModel>
        get() = MyAlbumsViewModel::class.java

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
abstract class AlbumsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MyAlbumsViewModel::class)
    abstract fun provideMyAlbumsFragmentViewModel(viewModel: MyAlbumsViewModel): ViewModel
}

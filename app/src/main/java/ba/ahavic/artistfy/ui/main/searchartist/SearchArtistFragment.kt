package ba.ahavic.artistfy.ui.main.searchartist

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.FragmentSearchArtistBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

class SearchArtistFragment :
    BaseBoundFragment<SearchArtistViewModel, FragmentSearchArtistBinding>() {

    private val artistsAdapter by lazy {
        ArtistsAdapter(viewModel::actionArtistSelected)
    }

    override val layoutId: Int
        get() = R.layout.fragment_search_artist
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<SearchArtistViewModel>
        get() = SearchArtistViewModel::class.java

    override fun bindToViewModel() {
        viewDataBinding.recyclerArtists.run {
            adapter = artistsAdapter
            setItemViewCacheSize(15)
        }

        viewModel.artists.observe(viewLifecycleOwner, Observer {
            artistsAdapter.setData(it)
        })
    }
}

@Module
abstract class SearchArtistModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchArtistViewModel::class)
    abstract fun provideSearchArtistViewModel(viewModel: SearchArtistViewModel): ViewModel
}

package ba.ahavic.artistfy.ui.main.artist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.data.artist.Artist
import ba.ahavic.artistfy.data.artist.ArtistRepository
import ba.ahavic.artistfy.databinding.FragmentSearchArtistBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

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

class SearchArtistViewModel @Inject constructor(private val artistRepository: ArtistRepository) :
    BaseViewModel() {

    private val _artists = MutableLiveData<List<Artist>>()
    val artists: LiveData<List<Artist>> = _artists

    val searchText = MutableLiveData<String>()

    fun actionArtistSelected(artist: Artist) {
        // go to top albums
    }

    fun actionSearchArtists() {
        launch {
            searchText.value?.let {
                isLoading(true)
                _artists.value = artistRepository.getArtists(it)
                isLoading(false)
            }
        }
    }
}
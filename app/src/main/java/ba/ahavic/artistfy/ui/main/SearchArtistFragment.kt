package ba.ahavic.artistfy.ui.main

import androidx.lifecycle.ViewModel
import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.FragmentSearchArtistBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

class SearchArtistFragment : BaseBoundFragment<SearchArtistViewModel, FragmentSearchArtistBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_search_artist
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<SearchArtistViewModel>
        get() = SearchArtistViewModel::class.java

    override fun bindToViewModel() {

    }
}

@Module
abstract class SearchArtistModule {
    @Binds
    @IntoMap
    @ViewModelKey(SearchArtistViewModel::class)
    abstract fun provideSearchArtistViewModel(viewModel: SearchArtistViewModel): ViewModel
}


class SearchArtistViewModel @Inject constructor(): BaseViewModel() {

}
package ba.ahavic.artistfy.ui.main

import androidx.lifecycle.ViewModel
import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.FragmentMainBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import ba.ahavic.artistfy.ui.base.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Inject

class MainFragment : BaseBoundFragment<MainFragmentViewModel, FragmentMainBinding>() {

    override val layoutId: Int
        get() = R.layout.fragment_main
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<MainFragmentViewModel>
        get() = MainFragmentViewModel::class.java

    override fun bindToViewModel() {

    }
}

class MainFragmentViewModel @Inject constructor(): BaseViewModel() {
}

@Module
abstract class MainFragmentModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    abstract fun bindMainFragmentViewModel(viewModel: MainFragmentViewModel): ViewModel
}

package ba.ahavic.artistfy.ui.main

import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.FragmentMainBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundFragment
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel

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

class MainFragmentViewModel: BaseViewModel() {

}
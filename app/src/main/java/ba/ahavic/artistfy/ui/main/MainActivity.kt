package ba.ahavic.artistfy.ui.main

import ba.ahavic.artistfy.BR
import ba.ahavic.artistfy.R
import ba.ahavic.artistfy.databinding.ActivityMainBinding
import ba.ahavic.artistfy.ui.base.view.BaseBoundActivity
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel

class MainActivity : BaseBoundActivity<MainViewModel, ActivityMainBinding>() {

    override val layoutId: Int
        get() = R.layout.activity_main
    override val viewModelNameRId: Int
        get() = BR.viewModel
    override val viewModelClass: Class<MainViewModel>
        get() = MainViewModel::class.java

    override fun bindToViewModel() {

    }
}

class MainViewModel: BaseViewModel() {

}

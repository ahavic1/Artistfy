package ba.ahavic.artistfy.ui.base.view

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import javax.inject.Inject

abstract class BaseBoundActivity<ViewModelType : BaseViewModel, ViewDataBindingType : ViewDataBinding> :
    BaseActivity<ViewDataBindingType>(),
    BoundView<ViewModelType> {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    final override lateinit var viewModel: ViewModelType

    override fun preInflate() {
        super.preInflate()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        lifecycle.addObserver(viewModel)
    }

    override fun postInflate(viewDataBinding: ViewDataBinding?) {
        super.postInflate(viewDataBinding)
        viewDataBinding?.let {
            if (viewModelNameRId != 0) {
                it.setVariable(viewModelNameRId, viewModel)
                it.lifecycleOwner = this
                it.executePendingBindings()
            }
        }
        bindToViewModel()
    }
}

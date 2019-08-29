package ba.ahavic.artistfy.ui.base.view

import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel

abstract class BaseBoundFragment<ViewModelType : BaseViewModel, ViewDataBindingType : ViewDataBinding> :
    BaseFragment<ViewDataBindingType>(), BoundView<ViewModelType> {

    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    final override lateinit var viewModel: ViewModelType

    override fun postInflate(viewDataBinding: ViewDataBinding?) {
        super.postInflate(viewDataBinding)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        lifecycle.addObserver(viewModel)

        viewDataBinding?.let {
            val viewModelRId = viewModelNameRId
            if (viewModelRId != 0) {
                it.setVariable(viewModelRId, viewModel)
                it.lifecycleOwner = viewLifecycleOwner
                it.executePendingBindings()
            }
        }
        bindToViewModel()
    }
}

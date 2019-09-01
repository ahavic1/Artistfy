package ba.ahavic.artistfy.ui.base.view

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.view.inputmethod.InputMethodManager
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import ba.ahavic.artistfy.showDialog
import ba.ahavic.artistfy.ui.base.viewmodel.BaseError
import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel
import ba.ahavic.artistfy.ui.base.viewmodel.NavigationAction
import javax.inject.Inject

abstract class BaseBoundFragment<ViewModelType : BaseViewModel, ViewDataBindingType : ViewDataBinding> :
    BaseFragment<ViewDataBindingType>(), BoundView<ViewModelType> {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    final override lateinit var viewModel: ViewModelType

    override fun postInflate(viewDataBinding: ViewDataBinding?) {
        super.postInflate(viewDataBinding)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        lifecycle.addObserver(viewModel)

        arguments?.let {
            viewModel.arguments = it
        }

        viewDataBinding?.let {
            val viewModelRId = viewModelNameRId
            if (viewModelRId != 0) {
                it.setVariable(viewModelRId, viewModel)
                it.lifecycleOwner = viewLifecycleOwner
                it.executePendingBindings()
            }
        }

        bindToViewModel()
        setNavigationObserver()
        setDefaultErrorObserver()
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

    protected open fun setDefaultErrorObserver() {
        viewModel.error.observe(viewLifecycleOwner, Observer {
            if (it !is BaseError.FeatureError)
                context!!.showDialog(it.title, it.description, DialogInterface.OnDismissListener { dialog ->
                    dialog.dismiss()
                })
        })
    }

    private fun setNavigationObserver() {
        viewModel.navigationAction.observe(viewLifecycleOwner, Observer { navAction ->
            when (navAction) {
                is NavigationAction.To -> getNavController().navigate(navAction.directions)
                is NavigationAction.Back -> getNavController().navigateUp()
            }
        })
    }
}

fun Fragment.getNavController(): NavController = NavHostFragment.findNavController(this)

fun Fragment.hideKeyboard() {
    try {
        val imm = context!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow((context as Activity).window.currentFocus!!.windowToken, 0)
    } catch (e: NullPointerException) {
    }
}
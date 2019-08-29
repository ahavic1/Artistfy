package ba.ahavic.artistfy.ui.base.view

import ba.ahavic.artistfy.ui.base.viewmodel.BaseViewModel

/**
 * Represent View that can be bound to ViewModel
 */
interface BoundView<ViewModelType : BaseViewModel> {

    /**
     * Provides name of ViewModel variable for data binding
     */
    val viewModelNameRId: Int

    /**
     * Provides ViewModel for this View. ViewModel is injected into the View
     * and accessible from this field
     */
    var viewModel: ViewModelType

    /**
     * Provide class of this view's ViewModel.
     * Required for ViewModelProviders so ViewModel can be injected
     */
    val viewModelClass: Class<ViewModelType>

    /**
     * Invoked when everything is ready.
     * Implement to do final bindings to ViewModel.
     */
    fun bindToViewModel()
}

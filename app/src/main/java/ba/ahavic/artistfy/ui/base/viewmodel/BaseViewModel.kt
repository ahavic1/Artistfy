package ba.ahavic.artistfy.ui.base.viewmodel

import android.os.Bundle
import androidx.lifecycle.*
import androidx.navigation.NavDirections
import ba.ahavic.artistfy.ui.base.SingleLiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel(), LifecycleObserver {

    private val _error = MutableLiveData<BaseError>()
    val error: LiveData<BaseError>
        get() = _error

    private val _loading = SingleLiveEvent<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _loading

    private val _navigationAction = SingleLiveEvent<NavigationAction>()
    val navigationAction: LiveData<NavigationAction> = _navigationAction

    var arguments: Bundle = Bundle()

    /**
     * [_coroutineExceptionHandler] context element is used as generic catch block of coroutine.
     */
    private var _coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // TODO(Handle exception)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    protected open fun onLifeCycleOwnerResume() {
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    protected open fun onLifeCycleOwnerPause() {
    }

    protected fun setError(error: BaseError) {
        _error.value = error
    }

    protected fun isLoading(isLoading: Boolean) {
        _loading.postValue(isLoading)
    }

    protected fun navigate(navAction: NavigationAction) {
        _navigationAction.value = navAction
    }

    protected fun navigate(navDirections: NavDirections) {
        _navigationAction.value = NavigationAction.To(navDirections)
    }

    protected fun launch(block: suspend CoroutineScope.() -> Unit) {
        try {
            viewModelScope.launch(_coroutineExceptionHandler) {
                block()
            }
        } catch (exception: Exception) {

        } finally {
            isLoading(false)
        }
    }
}

sealed class NavigationAction {
    data class To(val directions: NavDirections): NavigationAction()
    object Back: NavigationAction()
}
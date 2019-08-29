package ba.ahavic.artistfy.ui.base.viewmodel

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    /**
     * [_coroutineExceptionHandler] context element is used as generic catch block of coroutine.
     */
    private var _coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        // TODO(Handle exception)
    }

    protected fun setError(error: BaseError) {
        _error.postValue(error)
    }

    protected fun isLoading(isLoading: Boolean) {
        _loading.postValue(isLoading)
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

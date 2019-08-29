package ba.ahavic.artistfy.ui.base.view

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

/**
 * Base activity for all activities.
 * [ViewDataBindingType] type parameter is required.
 */
abstract class BaseActivity<ViewDataBindingType : ViewDataBinding> : AppCompatActivity(),
    LifecycleOwner {

    /**
     * Provides layout id of this view
     */
    @get:LayoutRes
    abstract val layoutId: Int


    protected lateinit var viewDataBinding: ViewDataBindingType

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        preInflate()
        if (layoutId != 0) {
            viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        }
        postInflate(viewDataBinding)
    }

    /**
     * Invoked before view inflation.
     */
    protected open fun preInflate() {}

    /**
     * Invoked after view inflation.
     * Called even when view is not inflated in which case [viewDataBinding] parameter is null.
     */
    protected open fun postInflate(viewDataBinding: ViewDataBinding?) {}
}

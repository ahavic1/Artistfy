package ba.ahavic.artistfy.ui.base.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {

    @BindingAdapter("app:visible")
    @JvmStatic
    fun isVisible(view: View, visible: Boolean?) {
        view.visibility = if (visible == true) View.VISIBLE else View.GONE
    }

    @BindingAdapter("app:src")
    @JvmStatic
    fun setSrc(imageView: ImageView, path: String?) {
        if (path?.isNotEmpty() == true) {
            Picasso.get().load(path).into(imageView)
        }
    }
}
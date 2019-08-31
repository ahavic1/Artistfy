package ba.ahavic.artistfy.ui.base.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import java.io.File

object BindingAdapters {

    @BindingAdapter("app:visible")
    @JvmStatic
    fun isVisible(view: View, visible: Boolean?) {
        view.visibility = if (visible == true) View.VISIBLE else View.GONE
    }

    @BindingAdapter("app:src")
    @JvmStatic
    fun setSrc(imageView: ImageView, uri: String?) {
        if (uri?.isNotEmpty() == true) {
            val filePath = File(uri)
            if (filePath.exists()) {
                Picasso.get().load(filePath).into(imageView)
            } else {
                Picasso.get().load(uri).into(imageView)
            }
        }
    }
}
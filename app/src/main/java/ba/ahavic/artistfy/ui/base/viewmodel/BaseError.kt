package ba.ahavic.artistfy.ui.base.viewmodel

import ba.ahavic.artistfy.R

sealed class BaseError(val title: Int, val description: Int){
    abstract class FeatureError(title: Int, description: Int = R.string.empty): BaseError(title, description)
}

object DefaultError: BaseError(R.string.all_error_default_title, R.string.all_error_default_description)
object ServerError: BaseError(R.string.all_error_default_title, R.string.all_error_default_description)

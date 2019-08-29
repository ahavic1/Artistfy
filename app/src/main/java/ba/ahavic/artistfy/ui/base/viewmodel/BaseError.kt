package ba.ahavic.artistfy.ui.base.viewmodel

sealed class BaseError(val title: Int, val description: Int){
    abstract class FeatureError(title: Int, description: Int): BaseError(title, description)
}
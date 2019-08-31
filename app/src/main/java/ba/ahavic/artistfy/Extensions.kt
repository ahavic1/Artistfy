package ba.ahavic.artistfy

import androidx.lifecycle.MutableLiveData

fun <T> T.asMutableLiveData(): MutableLiveData<T>
    = MutableLiveData<T>().apply { value = this@asMutableLiveData }
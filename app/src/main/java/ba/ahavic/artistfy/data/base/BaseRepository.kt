package ba.ahavic.artistfy.data.base

import ba.ahavic.artistfy.data.base.network.ApiError
import ba.ahavic.artistfy.data.base.network.DefaultErrorMapper
import ba.ahavic.artistfy.data.base.network.ErrorMapper
import kotlinx.coroutines.Dispatchers
import retrofit2.Response
import javax.inject.Inject

abstract class BaseRepository {
    @Inject
    protected lateinit var errorMapper: DefaultErrorMapper
    @Inject
    protected lateinit var dispachers: Dispatchers
}

fun <T> Response<List<T>>.asBodyList(errorMapper: ErrorMapper): List<T> =
    if (isSuccessful) body() ?: listOf() else throw AppException(errorMapper.parseError(this))

inline fun <reified T> Response<T>.asBody(errorMapper: ErrorMapper): T {
    if (isSuccessful) {
        return if (T::class.java.isInstance(Unit)) {
            Unit as T
        } else
            body() ?: T::class.java.newInstance()
    }
    throw AppException(errorMapper.parseError(this))
}

data class AppException(val apiError: ApiError): Exception()

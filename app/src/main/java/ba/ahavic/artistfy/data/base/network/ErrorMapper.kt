package ba.ahavic.artistfy.data.base.network

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

interface ErrorMapper {
    fun <T> parseError(response: Response<T>): ApiError
}

@Singleton
class DefaultErrorMapper @Inject constructor(private val retrofit: Retrofit) : ErrorMapper {

    private val errorConverter: Converter<ResponseBody, ApiError> by lazy {
        retrofit.responseBodyConverter<ApiError>(ApiError::class.java, emptyArray())
    }

    override fun <T> parseError(response: Response<T>): ApiError {
        return try {
            errorConverter.convert(response.errorBody())
        } catch (e: IOException) {
            ApiError(e.localizedMessage, e.localizedMessage)
        }
    }
}

data class ApiError(val error: String, val message: String)

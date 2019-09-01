package ba.ahavic.artistfy.data.base.network

import ba.ahavic.artistfy.ui.base.AppError
import ba.ahavic.artistfy.ui.base.ReasonOfError
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

interface ErrorMapper {
    fun <T> parseError(response: Response<T>): AppError
    fun parseError(exception: Exception): AppError
}

@Singleton
class DefaultErrorMapper @Inject constructor(private val retrofit: Retrofit) : ErrorMapper {
    private val errorConverter: Converter<ResponseBody, ApiError> by lazy {
        retrofit.responseBodyConverter<ApiError>(ApiError::class.java, emptyArray())
    }

    override fun <T> parseError(response: Response<T>): AppError {
        return try {
            errorConverter.convert(response.errorBody()).copy(code = response.code()).getAppError()
        } catch (ex: IOException) {
            parseExceptions(ex)
        }
    }

    override fun parseError(exception: Exception): AppError {
        return parseExceptions(exception)
    }


    private fun parseExceptions(ex: Exception): AppError {
        return when (ex) {
            is UnknownHostException -> AppError(ReasonOfError.UnKnownHost)
            else -> AppError(ReasonOfError.GenericError)
        }
    }
}

data class ApiError(val code: Int, val error: Int, val message: String) {

    fun getAppError(): AppError {
        return when (code) {
            500 -> AppError(ReasonOfError.ServerError)
            in 300..500 -> getApiError(error, message)
            else -> AppError(ReasonOfError.GenericError)
        }
    }

    private fun getApiError(error: Int, message: String): AppError {
        return when (error) {
            3 -> AppError(ReasonOfError.InvalidRequestMethod)
            6 -> AppError(ReasonOfError.InvalidParameters)
            10 -> AppError(ReasonOfError.InvalidAPIKey)
            else -> AppError(ReasonOfError.GenericError)
        }
    }
}


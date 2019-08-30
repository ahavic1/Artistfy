package ba.ahavic.artistfy.data.base.network

import androidx.annotation.NonNull
import ba.ahavic.artistfy.BuildConfig
import ba.ahavic.artistfy.data.ClientConfig
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton


class AuthInterceptor @Inject constructor(private val networkConfig: ClientConfig): Interceptor {

    @Throws(IOException::class)
    override fun intercept(@NonNull chain: Interceptor.Chain): Response? {
        val originalRequest = chain.request()

        // Ignore calls to get images
        if (!originalRequest.url().toString().contains(BuildConfig.baseUrl)) {
            return chain.proceed(originalRequest)
        }

        val originalHttpUrl = originalRequest.url()
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY_QUERY, networkConfig.clientId)
            .addQueryParameter(FORMAT, "json")
            .build()

        val request = originalRequest.newBuilder()
            .url(url)
            .build()

        return chain.proceed(request)
    }

    companion object {
        private const val API_KEY_QUERY = "api_key"
        private const val FORMAT = "format"
    }
}


@Module
abstract class AuthInterceptorModule {
    @Binds
    @IntoSet
    @Singleton
    abstract fun provideAuthInterceptor(interceptor: AuthInterceptor): Interceptor
}
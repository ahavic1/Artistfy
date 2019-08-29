package ba.ahavic.artistfy.ui.base.di.module

import ba.ahavic.artistfy.BuildConfig
import ba.ahavic.artistfy.data.ClientConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppConfigModule {

    @Provides
    @JvmStatic
    @Singleton
    fun provideClientConfig(): ClientConfig = object : ClientConfig {
        override val clientId: String
            get() = BuildConfig.clientId
        override val clientSecret: String
            get() = BuildConfig.clientSecret
    }
}

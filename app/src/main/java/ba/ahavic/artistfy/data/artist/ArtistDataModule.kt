package ba.ahavic.artistfy.data.artist

import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class ArtistDataModule {

    @Binds
    @Singleton
    abstract fun provideArtistRepository(repository: ArtistRepositoryImpl): ArtistRepository

    @Module
    companion object {
        @Provides
        @Singleton
        @JvmStatic
        fun provideArtistApi(retrofit: Retrofit): ArtistApi
            = retrofit.create(ArtistApi::class.java)
    }
}

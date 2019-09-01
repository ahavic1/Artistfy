package ba.ahavic.artistfy.data.album

import ba.ahavic.artistfy.data.base.db.AppDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class AlbumDataModule {

    @Binds
    @Singleton
    abstract fun provideAlbumRepository(albumRepository: AlbumRepositoryImpl): AlbumRepository

    @Module
    companion object {

        @Provides
        @Singleton
        @JvmStatic
        fun provideAlbumDao(appDatabase: AppDatabase): AlbumDao = appDatabase.albumDao()

        @Provides
        @Singleton
        @JvmStatic
        fun provideAlbumApi(retrofit: Retrofit): AlbumApi = retrofit.create(AlbumApi::class.java)
    }
}

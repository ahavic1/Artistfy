package ba.ahavic.artistfy.data.artist

import ba.ahavic.artistfy.BuildConfig
import ba.ahavic.artistfy.data.base.BaseRepository
import ba.ahavic.artistfy.data.base.asBody
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

interface ArtistRepository {
    suspend fun getArtists(artistName: String, page: String = "1"): List<Artist>
}

class ArtistRepositoryImpl @Inject constructor(private val artistApi: ArtistApi)
    : BaseRepository(), ArtistRepository {

    override suspend fun getArtists(artistName: String, page: String): List<Artist> {
        return artistApi.getArtistsAsync(artistName = artistName, page = page).await()
            .asBody(errorMapper)
            .results.artistmatches.artist
            .map {
                Artist(
                    it.mbid,
                    it.name,
                    it.url,
                    it.image.find { image -> image.size.equals("large") }?.url
                )
            }
    }
}

interface ArtistApi {
    @GET("2.0/")
    fun getArtistsAsync(
        @Query("method") method: String = "artist.search",
        @Query("limit") limitPerPage: String = BuildConfig.limitPerPage,
        @Query("page") page: String = "1",
        @Query("artist") artistName: String
    ): Deferred<Response<Data>>
}

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

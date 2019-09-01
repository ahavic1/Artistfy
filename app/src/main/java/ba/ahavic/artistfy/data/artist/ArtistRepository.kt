package ba.ahavic.artistfy.data.artist

import ba.ahavic.artistfy.BuildConfig
import ba.ahavic.artistfy.data.Mappers
import ba.ahavic.artistfy.data.album.Album
import ba.ahavic.artistfy.data.album.TopAlbumData
import ba.ahavic.artistfy.data.base.BaseRepository
import ba.ahavic.artistfy.data.base.asBody
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject

interface ArtistRepository {
    suspend fun getArtists(artistName: String, page: String = "1"): List<Artist>
    suspend fun getTopAlbums(artistName: String): List<Album>
}

class ArtistRepositoryImpl @Inject constructor(private val artistApi: ArtistApi) : BaseRepository(),
    ArtistRepository {

    override suspend fun getArtists(artistName: String, page: String): List<Artist> {
        return artistApi.getArtistsAsync(artistName = artistName, page = page).await()
            .asBody(errorMapper)
            .results.artistmatches.artist
            .map { Mappers.mapArtist(it) }
    }

    override suspend fun getTopAlbums(artistName: String): List<Album> {
        return artistApi.getTopAlbumsAsync(artistName = artistName).await()
            .asBody(errorMapper)
            .topalbums.album
            .map { Mappers.mapAlbum(it) }
    }
}

interface ArtistApi {
    @GET("2.0/")
    fun getArtistsAsync(
        @Query("method") method: String = "artist.search",
        @Query("limit") limitPerPage: String = BuildConfig.limitPerPage,
        @Query("page") page: String = "1",
        @Query("artist") artistName: String
    ): Deferred<Response<ArtistData>>

    @GET("2.0/")
    fun getTopAlbumsAsync(
        @Query("method") method: String = "artist.gettopalbums",
        @Query("limit") limitPerPage: String = BuildConfig.limitPerPage,
        @Query("page") page: String = "1",
        @Query("artist") artistName: String
    ): Deferred<Response<TopAlbumData>>
}

package ba.ahavic.artistfy.data.album

import androidx.room.Dao
import androidx.room.Query
import ba.ahavic.artistfy.data.Mappers
import ba.ahavic.artistfy.data.base.BaseRepository
import ba.ahavic.artistfy.data.base.asBody
import ba.ahavic.artistfy.data.base.db.BaseDao
import ba.ahavic.artistfy.data.base.network.ImageDownloader
import ba.ahavic.artistfy.ui.base.AppError
import ba.ahavic.artistfy.ui.base.AppException
import ba.ahavic.artistfy.ui.base.ReasonOfError
import ba.ahavic.artistfy.ui.data.Album
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun getAlbum(album: Album): Album
    suspend fun saveAlbum(album: Album): Boolean
    suspend fun deleteAlbum(album: Album): Boolean
}

class AlbumRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val albumApi: AlbumApi,
    private val imageDownloader: ImageDownloader
) : BaseRepository(), AlbumRepository {

    override suspend fun getAlbums(): List<Album> = albumDao.getAlbums()

    @Suppress("SENSELESS_COMPARISON")
    override suspend fun getAlbum(album: Album): Album = withContext(dispachers.IO) {
        val localCopy = albumDao.getAlbumById(album.mbid)
        if (localCopy != null) {
            return@withContext localCopy
        }

        return@withContext albumApi.getAlbumAsync(
            artistName = album.artist.name,
            albumName = album.name
        ).await()
            .asBody(errorMapper)
            .album
            .let { Mappers.mapAlbumInfo(it).copy(artist = album.artist) }
    }

    override suspend fun saveAlbum(album: Album): Boolean = withContext(dispachers.IO) {
        try {
            val imagePath = album.image?.let { imageDownloader.downloadImage(it) }
            albumDao.save(album.copy(image = imagePath, cached = true))
            return@withContext true
        } catch (ex: Exception) {
            throw AppException(AppError(ReasonOfError.DatabaseSaveFailed))
        }
    }

    override suspend fun deleteAlbum(album: Album): Boolean {
        return try {
            albumDao.deleteAlbumById(album.mbid)
            true
        } catch (ex: Exception) {
            false
        }
    }
}

@Dao
interface AlbumDao : BaseDao<Album> {

    @Query("SELECT * FROM Album")
    suspend fun getAlbums(): List<Album>

    @Query("SELECT * FROM Album WHERE mbid = :albumId")
    suspend fun getAlbumById(albumId: String): Album

    @Query("DELETE FROM Album WHERE mbid == :id")
    suspend fun deleteAlbumById(id: String): Int
}


interface AlbumApi {
    @GET("2.0/")
    fun getAlbumAsync(
        @retrofit2.http.Query("method") method: String = "album.getinfo",
        @retrofit2.http.Query("artist") artistName: String,
        @retrofit2.http.Query("album") albumName: String
    ): Deferred<Response<AlbumData>>
}

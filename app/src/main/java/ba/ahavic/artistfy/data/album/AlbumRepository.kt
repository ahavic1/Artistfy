package ba.ahavic.artistfy.data.album

import androidx.room.Dao
import androidx.room.Query
import ba.ahavic.artistfy.data.artist.Artist
import ba.ahavic.artistfy.data.base.BaseRepository
import ba.ahavic.artistfy.data.base.db.BaseDao
import ba.ahavic.artistfy.data.base.network.ImageDownloader
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface AlbumRepository {
    suspend fun getAlbums(): List<Album>
    suspend fun saveAlbum(album: Album): Boolean
    suspend fun deleteAlbum(album: Album): Boolean
}

class AlbumRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val imageDownloader: ImageDownloader
) : BaseRepository(), AlbumRepository {

    override suspend fun getAlbums(): List<Album> = albumDao.getAlbums()

    override suspend fun saveAlbum(album: Album): Boolean = withContext(dispachers.IO) {
        try {
            album.image?.let {
                val localImagePath = imageDownloader.downloadImage(it)
                albumDao.save(album.copy(
                    image = localImagePath,
                    cached = true
                ))
            } ?: albumDao.save(album)
            true
        } catch (ex: Exception) {
            false
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

    @Query("DELETE FROM Album WHERE mbid == :id")
    suspend fun deleteAlbumById(id: String): List<Album>
}

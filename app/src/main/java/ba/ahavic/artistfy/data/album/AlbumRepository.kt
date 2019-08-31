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
    suspend fun deleteAlbum(): Boolean
    suspend fun getAlbumById(albumId: String): Album
}

class AlbumRepositoryImpl @Inject constructor(
    private val albumDao: AlbumDao,
    private val imageDownloader: ImageDownloader
) : BaseRepository(), AlbumRepository {

    override suspend fun getAlbumById(albumId: String): Album {
        return Album(
            "1212",
            "Klika",
            "htttps//ademir",
            Artist(
                "21212",
                "Ademir Havic",
                "https//Ademir"
            ),
            "htttps//ademir",
            Wiki(
                "29/08/2003",
                """Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                    |Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
                    |when an unknown printer took a galley of type and scrambled it to make a 
                    |type specimen book. It has survived not only five centuries, but also the 
                    |leap into electronic typesetting, remaining essentially unchanged. 
                    |It was popularised in the 1960s with the release of Letraset sheets 
                    |containing Lorem Ipsumpassages, and more recently with desktop publishing 
                    |software like Aldus PageMaker including versions of Lorem Ipsum.""".trimMargin(),
                "sasas"
            )
        )
    }

    override suspend fun getAlbums(): List<Album> {
        //return albumDao.getAlbums()
        val list = arrayListOf<Album>()

        for (i in 1..50) {
            list.add(Album(
                "$i",
                "Klika",
                "htttps//ademir",
                Artist(
                    "${i + 1}",
                    "Ademir Havic",
                    "https//Ademir"
                ),
                "htttps//ademir"
            )
            )
        }

        return list
    }

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

    override suspend fun deleteAlbum(): Boolean {
        TODO("not implemented")
    }
}

@Dao
interface AlbumDao : BaseDao<Album> {

    @Query("SELECT * FROM album")
    suspend fun getAlbums(): List<Album>
}

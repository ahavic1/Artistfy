package ba.ahavic.artistfy.data.album

import androidx.room.Entity
import androidx.room.PrimaryKey
import ba.ahavic.artistfy.data.artist.Artist
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Album(
    @PrimaryKey val mbid: String,
    val name: String,
    val url: String,
    val artist: Artist? = null,
    val image: String?,
    val wiki: Wiki? = null,
    val tracks: List<Track>? = null,
    val cached: Boolean = false
) : Serializable

data class Track(val name: String, val url: String, val duration: String)

data class Image(@SerializedName("#text") val url: String, val size: String)

data class Wiki(val published: String, val summary: String, val content: String): Serializable

data class AlbumDTO(
    val mbid: String?,
    val name: String,
    val playcount: Int,
    val url: String,
    val artist: Artist,
    val image: List<Image>?,
    val tracks: TracksDTO?,
    val wiki: Wiki? = null
)

data class AlbumInfoDTO(
    val mbid: String?,
    val name: String,
    val playcount: Int,
    val url: String,
    val artist: String,
    val image: List<Image>?,
    val tracks: TracksDTO?,
    val wiki: Wiki? = null
)

data class AlbumData(val album: AlbumInfoDTO)

data class TracksDTO(val track: List<Track>)

data class TopAlbumsDTO(val album: List<AlbumDTO>)

data class TopAlbumData(val topalbums: TopAlbumsDTO)
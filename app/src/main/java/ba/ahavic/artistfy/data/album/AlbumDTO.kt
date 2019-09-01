package ba.ahavic.artistfy.data.album

import ba.ahavic.artistfy.ui.data.Artist
import ba.ahavic.artistfy.ui.data.Track
import ba.ahavic.artistfy.ui.data.Wiki
import com.google.gson.annotations.SerializedName

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

data class Image(@SerializedName("#text") val url: String, val size: String)

data class AlbumData(val album: AlbumInfoDTO)

data class TracksDTO(val track: List<Track>)

data class TopAlbumsDTO(val album: List<AlbumDTO>)

data class TopAlbumData(val topalbums: TopAlbumsDTO)
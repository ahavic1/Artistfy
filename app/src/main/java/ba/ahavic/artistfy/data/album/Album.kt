package ba.ahavic.artistfy.data.album

import androidx.room.Entity
import androidx.room.PrimaryKey
import ba.ahavic.artistfy.data.artist.Artist
import com.google.gson.annotations.SerializedName

@Entity
class Album(
    @PrimaryKey val mbid: String, val name: String,
    val url: String,
    val artist: Artist,
    val images: List<Image>,
    val wiki: Wiki? = null
)

data class Image(@SerializedName("#text") val url: String, val size: String)

data class Wiki(val published: String, val summary: String, val content: String)


package ba.ahavic.artistfy.data.album

import androidx.room.Entity
import androidx.room.PrimaryKey
import ba.ahavic.artistfy.data.artist.Artist

@Entity
class Album(
    @PrimaryKey val mbid: String, val name: String,
    val url: String,
    val artist: Artist,
    val images: List<Image>,
    val wiki: Wiki? = null
)

data class Image(val url: String, val size: ImageSize)

data class Wiki(val published: String, val summary: String, val content: String)

enum class ImageSize {
    Small, Medium, Large, ExtraLarge
}

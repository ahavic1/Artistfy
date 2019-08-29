package ba.ahavic.artistfy.data.album

import androidx.room.Entity
import androidx.room.PrimaryKey
import ba.ahavic.artistfy.data.artist.Artist

@Entity
class Album(
    @PrimaryKey val mbid: String, val name: String,
    val url: String,
    val artist: Artist,
    val images: List<Image>
)

@Entity
data class Image(@PrimaryKey val url: String, val size: ImageSize)

enum class ImageSize {
    Small, Medium, Large, ExtraLarge
}

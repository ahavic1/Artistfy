package ba.ahavic.artistfy.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Album(
    @PrimaryKey val mbid: String,
    val name: String,
    val url: String,
    val artist: Artist,
    val image: String?,
    val wiki: Wiki? = null,
    val tracks: List<Track>? = null,
    val cached: Boolean = false
) : Serializable

data class Track(val name: String, val url: String, val duration: String)

data class Wiki(val published: String, val summary: String, val content: String): Serializable

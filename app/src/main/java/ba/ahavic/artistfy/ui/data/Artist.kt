package ba.ahavic.artistfy.ui.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Artist(
    @PrimaryKey val mbid: String,
    val name: String,
    val url: String,
    val imageUrl: String? = null
) : Serializable

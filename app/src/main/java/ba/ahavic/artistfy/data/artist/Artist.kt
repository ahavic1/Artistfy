package ba.ahavic.artistfy.data.artist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Artist(@PrimaryKey val mbid: String, val name: String, val url: String)
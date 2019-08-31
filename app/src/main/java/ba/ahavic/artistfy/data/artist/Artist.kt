package ba.ahavic.artistfy.data.artist

import androidx.room.Entity
import androidx.room.PrimaryKey
import ba.ahavic.artistfy.data.album.Image
import java.io.Serializable

@Entity
data class Artist(
    @PrimaryKey val mbid: String,
    val name: String,
    val url: String,
    val imageUrl: String? = null
) : Serializable

data class ArtistDTO(
    val mbid: String,
    val name: String,
    val url: String,
    val listeners: String,
    val image: List<Image>
)

data class ArtistMatchesDTO(val artist: List<ArtistDTO>)

data class ResultsDTO(val artistmatches: ArtistMatchesDTO)

data class ArtistData(val results: ResultsDTO)

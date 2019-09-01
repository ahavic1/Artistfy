package ba.ahavic.artistfy.data.artist

import ba.ahavic.artistfy.data.album.Image

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

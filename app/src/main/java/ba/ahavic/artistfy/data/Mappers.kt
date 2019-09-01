package ba.ahavic.artistfy.data

import ba.ahavic.artistfy.data.album.AlbumDTO
import ba.ahavic.artistfy.data.album.AlbumInfoDTO
import ba.ahavic.artistfy.data.artist.ArtistDTO
import ba.ahavic.artistfy.ui.data.Album
import ba.ahavic.artistfy.ui.data.Artist

object Mappers {

    fun mapArtist(artistDTO: ArtistDTO): Artist = artistDTO.let {
        Artist(
            it.mbid,
            it.name,
            it.url,
            it.image.find { image ->
                image.size.equals("large") || image.size.equals("medium")
            }?.url
        )
    }

    fun mapAlbum(albumDTO: AlbumDTO): Album = albumDTO.let {
        Album(
            mbid = it.mbid ?: it.name,
            name = it.name,
            url = it.url,
            artist = it.artist,
            image = it.image?.find { image ->
                image.size.equals("large") || image.size.equals("medium")
            }?.url!!,
            wiki = it.wiki,
            tracks = it.tracks?.track,
            cached = false
        )
    }

    fun mapAlbumInfo(albumInfoDTO: AlbumInfoDTO): Album = albumInfoDTO.let {
        Album(
            mbid = it.mbid ?: it.name,
            name = it.name,
            url = it.url,
            artist = Artist(it.artist, it.artist, it.artist),
            image = it.image?.find { image ->
                image.size.equals("large") || image.size.equals("medium")
            }?.url!!,
            wiki = it.wiki,
            tracks = it.tracks?.track,
            cached = false
        )
    }
}

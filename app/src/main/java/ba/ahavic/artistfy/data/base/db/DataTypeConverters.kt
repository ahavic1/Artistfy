package ba.ahavic.artistfy.data.base.db

import androidx.room.TypeConverter
import ba.ahavic.artistfy.data.album.Image
import ba.ahavic.artistfy.data.album.Wiki
import ba.ahavic.artistfy.data.artist.Artist
import com.google.gson.Gson
import java.lang.reflect.Type

class DataTypeConverters {

    @TypeConverter
    fun artistToJson(artist: Artist): String? = toJson(artist)

    @TypeConverter
    fun artistFromJson(artist: String): Artist? = fromJson<Artist>(artist)

    @TypeConverter
    fun imageToJson(image: List<Image>): String? = toJson(image)

    @TypeConverter
    fun imageFromJson(image: String): List<Image> = fromJson<List<Image>>(image)

    @TypeConverter
    fun wikiToJson(albumWiki: Wiki?): String? = toJson(albumWiki)

    @TypeConverter
    fun wikiFromJson(albumWiki: String): Wiki = fromJson<Wiki>(albumWiki)

    private inline fun <reified T> fromJson(value: String, type: Type): List<T> {
        return Gson().fromJson(value, type)
    }

    private inline fun <reified T> fromJson(value: String): T {
        return Gson().fromJson(value, T::class.java)
    }

    private inline fun <reified T> toJson(value: T): String {
        return Gson().toJson(value)
    }
}
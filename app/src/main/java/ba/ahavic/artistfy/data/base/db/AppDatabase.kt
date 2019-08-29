package ba.ahavic.artistfy.data.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ba.ahavic.artistfy.data.album.Album
import ba.ahavic.artistfy.data.album.AlbumDao
import ba.ahavic.artistfy.data.album.Image
import ba.ahavic.artistfy.data.artist.Artist
import javax.inject.Singleton

@Singleton
@TypeConverters(DataTypeConverters::class)
@Database(entities = [Artist::class, Album::class, Image::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}

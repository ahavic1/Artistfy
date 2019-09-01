package ba.ahavic.artistfy.data.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ba.ahavic.artistfy.data.album.AlbumDao
import ba.ahavic.artistfy.ui.data.Album
import javax.inject.Singleton

@Singleton
@TypeConverters(DataTypeConverters::class)
@Database(entities = [Album::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun albumDao(): AlbumDao
}

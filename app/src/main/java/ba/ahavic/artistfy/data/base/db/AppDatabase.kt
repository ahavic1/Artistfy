package ba.ahavic.artistfy.data.base.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ba.ahavic.artistfy.data.artist.Artist
import javax.inject.Singleton

@Singleton
@Database(entities = [Artist::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
}

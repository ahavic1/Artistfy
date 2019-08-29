package ba.ahavic.artistfy.data.base.di

import android.content.Context
import androidx.room.Room
import ba.ahavic.artistfy.data.base.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object CacheModule {

    private const val dbName: String = "artistfy-db"
    @Singleton
    @Provides
    @JvmStatic
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()
    }
}
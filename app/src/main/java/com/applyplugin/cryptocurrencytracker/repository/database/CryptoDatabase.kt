package com.applyplugin.cryptocurrencytracker.repository.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.applyplugin.cryptocurrencytracker.di.DatabaseModule.provideDatabase
import com.applyplugin.cryptocurrencytracker.repository.database.entity.CryptoEntity

@Database(
    entities = [CryptoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CryptoTypeConverter::class)
abstract class CryptoDatabase: RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

    companion object{
        @Volatile
        private var instance: CryptoDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: provideDatabase(context).also{ instance = it}
        }

    }

}
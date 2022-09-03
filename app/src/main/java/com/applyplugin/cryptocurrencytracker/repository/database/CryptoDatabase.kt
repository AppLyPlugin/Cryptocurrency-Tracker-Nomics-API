package com.applyplugin.cryptocurrencytracker.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [CryptoEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(CryptoTypeConverter::class)
abstract class CryptoDatabase: RoomDatabase() {

    abstract fun cryptodao(): CryptoDao

}
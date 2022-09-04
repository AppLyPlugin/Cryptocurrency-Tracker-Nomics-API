package com.applyplugin.cryptocurrencytracker.di

import android.content.Context
import androidx.room.Room
import com.applyplugin.cryptocurrencytracker.repository.database.CryptoDatabase
import com.applyplugin.cryptocurrencytracker.repository.database.CryptoTypeConverter
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        CryptoDatabase::class.java,
        DATABASE_NAME
    ).addTypeConverter(CryptoTypeConverter())
        .build()


    @Singleton
    @Provides
    fun provideDao(database: CryptoDatabase) = database.cryptoDao()

}
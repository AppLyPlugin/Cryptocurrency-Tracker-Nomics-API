package com.applyplugin.cryptocurrencytracker

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CryptocurrencyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
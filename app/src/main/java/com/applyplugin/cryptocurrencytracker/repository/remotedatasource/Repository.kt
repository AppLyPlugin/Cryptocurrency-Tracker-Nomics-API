package com.applyplugin.cryptocurrencytracker.repository.remotedatasource

import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource
) {

    val remoteSource = remoteDataSource

}
package com.applyplugin.cryptocurrencytracker

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.getSystemService
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.repository.Repository
import com.applyplugin.cryptocurrencytracker.util.NetworkResult
import dagger.Provides
import dagger.hilt.android.internal.Contexts.getApplication
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application) {

    var cryptoResponse: MutableLiveData<NetworkResult<List<CryptoResponse>>> = MutableLiveData()

    fun getCrypto(ids: String) = viewModelScope.launch {
        getCryptoSafeCall(ids)
    }

    private suspend fun getCryptoSafeCall(ids: String) {
        cryptoResponse.value = NetworkResult.Loading()
        if (hasInternetConnection()) {
            try {
                //val response = repository.remoteSource.getCrypto(ids, null, null, null)
                val response = repository.remoteSource.getAllCrypto()
                cryptoResponse.value = handleCryptoResponse(response)
            } catch (e: Exception) {
                cryptoResponse.value = NetworkResult.Error("Crypto Not Found")
                e.stackTrace
            }
        } else {
            cryptoResponse.value = NetworkResult.Error("No Internet Connection")
        }

    }

    private fun handleCryptoResponse(response: Response<List<CryptoResponse>>): NetworkResult<List<CryptoResponse>>? {

        when {
            response.message().toString().contains("timeout") -> {
                return NetworkResult.Error("Connection Timeout")
            }
            response.code() == 402 -> {
                return NetworkResult.Error("API Key Limited")
            }
            response.body()!!.toString().isNullOrEmpty() -> {
                return NetworkResult.Error("Crypto Not Found")
            }
            response.code() == 200 ||
                    response.isSuccessful -> {
                val crypto = response.body()
                return NetworkResult.Success(crypto!!)
            }
            else -> {
                return NetworkResult.Error(response.message())
            }
        }

    }

    private fun hasInternetConnection(): Boolean {

        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager

        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

        return true

    }

}
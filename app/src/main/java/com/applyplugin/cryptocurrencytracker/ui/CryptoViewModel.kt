package com.applyplugin.cryptocurrencytracker.ui

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.applyplugin.cryptocurrencytracker.repository.DataStoreRepository
import com.applyplugin.cryptocurrencytracker.util.Constants
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.API_KEY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.FILTER_FILTER
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.PER_PAGE
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_CURRENCY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_FILTER
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_IDS
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_INTERVAL
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_KEY
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_PAGE
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_PER_PAGE
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.QUERY_STATUS
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    application: Application,
    private val dataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var status = ""
    private var filter = FILTER_FILTER

    var networkStatus = false
    var backOnline = false

    val readFilter = dataStoreRepository.readFilters
    val readBackOnline = dataStoreRepository.readConnectivity.asLiveData()

    fun saveFilter(selectedFilter: String, selectedFilterId: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveFilter(selectedFilter, selectedFilterId)
        }

    fun saveBackOnline(backOnline : Boolean) =
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.saveConnectivity(backOnline)
        }

    fun applyQuery(): HashMap<String, String> {

        val queries: HashMap<String, String> = HashMap()

        viewModelScope.launch {
            readFilter.collect { value ->
                if(value.filter == Constants.Filter.all.filter
                    || value.filter == Constants.Filter.active.filter
                    || value.filter == Constants.Filter.inactive.filter
                    || value.filter == Constants.Filter.dead.filter){

                    status = value.filter

                }else if(value.filter == Constants.Filter.fNew.filter){
                    filter = value.filter
                }

            }

        }

        queries[QUERY_KEY] = API_KEY
        queries[QUERY_IDS] = ""
        queries[QUERY_CURRENCY] = ""
        queries[QUERY_INTERVAL] = ""
        queries[QUERY_STATUS] = status
        queries[QUERY_FILTER] = filter
        queries[QUERY_PER_PAGE] = PER_PAGE
        queries[QUERY_PAGE] = "1"

        while (queries.values.removeIf { it == "" }) {
        }

        return queries
    }

    fun networkStatus(){
        if(!networkStatus){
            Toast.makeText(getApplication(), "No Internet Connection", Toast.LENGTH_LONG).show()
            saveBackOnline(true)
        } else if(networkStatus){
            if(backOnline){
                Toast.makeText(getApplication(), "We're Back Online", Toast.LENGTH_LONG).show()
                saveBackOnline(false)
            }
        }
    }
}
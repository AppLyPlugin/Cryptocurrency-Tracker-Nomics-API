package com.applyplugin.cryptocurrencytracker.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.compose.ui.text.toUpperCase
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.applyplugin.cryptocurrencytracker.MainViewModel
import com.applyplugin.cryptocurrencytracker.R
import com.applyplugin.cryptocurrencytracker.adapter.CryptoAdapter
import com.applyplugin.cryptocurrencytracker.databinding.FragmentCryptoBinding
import com.applyplugin.cryptocurrencytracker.util.NetworkListener
import com.applyplugin.cryptocurrencytracker.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import java.util.*

@AndroidEntryPoint
class CryptoFragment : Fragment(), SearchView.OnQueryTextListener {

    private val args by navArgs<CryptoFragmentArgs>()

    private var _binding: FragmentCryptoBinding? = null
    private val binding get() = _binding!!

    private val mAdapter: CryptoAdapter by lazy { CryptoAdapter() }
    private val mainViewModel: MainViewModel by viewModels()
    private val cryptoViewModel: CryptoViewModel by viewModels()

    private lateinit var networkListener: NetworkListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCryptoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel

        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)

        setUpRecyclerView()

        cryptoViewModel.readBackOnline.observe(viewLifecycleOwner) {
            cryptoViewModel.backOnline = it
        }

        lifecycleScope.launch {
            networkListener = NetworkListener()
            networkListener.checkNetworkAvailability(requireContext())
                .collect() {
                    cryptoViewModel.networkStatus = it
                    cryptoViewModel.networkStatus()
                }
        }

        requestApiData()

        binding.filter.setOnClickListener {
            if (cryptoViewModel.networkStatus) {
                findNavController().navigate(R.id.action_cryptoFragment_to_cryptoFilterDialogFragment)
            } else {
                Toast.makeText(requireContext(), "No Internet Connection", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        return binding.root
    }

//    private fun readDatabase() {
//        lifecycleScope.launch {
//            mainViewModel.readCryptos.observe(viewLifecycleOwner) { database ->
//                if (database.isNotEmpty()) {
//                    Log.d("CryptoFragment", "readDatabase called!")
//                    mAdapter.setData(database[0].crypto)
//                    hideShimmerEffect()
//                }
//            }
//        }
//    }

    fun requestApiData() {
        Log.d("CryptoFragment", "requestApiData called!")
        mainViewModel.getCrypto(cryptoViewModel.applyQuery())
        mainViewModel.cryptoResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }

    fun searchApiData(searchQuery: String) {
        showShimmerEffect()
        mainViewModel.searchCrypto(cryptoViewModel.applySearchQuery(searchQuery.uppercase(Locale.getDefault())))
        mainViewModel.searchedCryptoResponse.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    loadDataFromCache()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        }
    }


    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readCryptos.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].crypto)
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    @Suppress("DEPRECATION")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.crypto_menu, menu)

        val search = menu.findItem(R.id.menu_search)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = true
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchApiData(query)
        }

        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun showShimmerEffect() {
        binding.cryptoFragmentShimmer.startShimmer()
        binding.recyclerview.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.cryptoFragmentShimmer.stopShimmer()
        binding.cryptoFragmentShimmer.visibility = View.GONE
        binding.recyclerview.visibility = View.VISIBLE
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
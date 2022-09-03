package com.applyplugin.cryptocurrencytracker.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.applyplugin.cryptocurrencytracker.MainViewModel
import com.applyplugin.cryptocurrencytracker.adapter.CryptoAdapter
import com.applyplugin.cryptocurrencytracker.databinding.FragmentCryptoBinding
import com.applyplugin.cryptocurrencytracker.util.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoFragment : Fragment() {

    private val mAdapter: CryptoAdapter by lazy { CryptoAdapter() }
    private val mainViewModel: MainViewModel by viewModels()
    private val cryptoViewModel: CryptoViewModel by viewModels()
    private lateinit var binding: FragmentCryptoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCryptoBinding.inflate(inflater, container, false)

        setUpRecyclerView()
        readDatabase()

        return binding.root
    }

    private fun readDatabase() {
        mainViewModel.readCryptos.observe(viewLifecycleOwner) { database ->
            if (database.isNotEmpty()) {
                Log.d("CryptoFragment", "readDatabase called!")
                mAdapter.setData(database[0].crypto)
                hideShimmerEffect()
            } else {
                requestApiData()
            }
        }
    }

    private fun requestApiData() {
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


    private fun setUpRecyclerView() {
        binding.recyclerview.adapter = mAdapter
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
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
}
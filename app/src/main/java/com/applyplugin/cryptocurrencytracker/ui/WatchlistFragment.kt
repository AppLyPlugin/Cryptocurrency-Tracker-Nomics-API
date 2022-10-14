package com.applyplugin.cryptocurrencytracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.applyplugin.cryptocurrencytracker.MainViewModel
import com.applyplugin.cryptocurrencytracker.adapter.CryptoAdapter
import com.applyplugin.cryptocurrencytracker.adapter.WatchlistAdapter
import com.applyplugin.cryptocurrencytracker.databinding.FragmentCryptoBinding
import com.applyplugin.cryptocurrencytracker.databinding.FragmentWatchlistBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WatchlistFragment : Fragment() {

    private var _binding: FragmentWatchlistBinding? = null
    private val binding get() = _binding!!

    private val mAdapter: WatchlistAdapter by lazy {
        WatchlistAdapter(
            requireActivity(),
            mainViewModel
        )
    }


    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWatchlistBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.mainViewModel = mainViewModel
        binding.mAdapter = mAdapter

        @Suppress("DEPRECATION")
        setHasOptionsMenu(true)

        setupRecyclerView(binding.recyclerview)

        return binding.root

    }

    private fun setupRecyclerView(recyclerview: RecyclerView) {
        recyclerview.adapter = mAdapter
        recyclerview.layoutManager = LinearLayoutManager(requireContext())

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

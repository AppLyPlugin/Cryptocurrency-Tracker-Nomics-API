package com.applyplugin.cryptocurrencytracker.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applyplugin.cryptocurrencytracker.databinding.WatcherFavoritesBinding

class WatcherFragment : Fragment() {

    private lateinit var binding: WatcherFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = WatcherFavoritesBinding.inflate(inflater)

        return binding.root

    }

}
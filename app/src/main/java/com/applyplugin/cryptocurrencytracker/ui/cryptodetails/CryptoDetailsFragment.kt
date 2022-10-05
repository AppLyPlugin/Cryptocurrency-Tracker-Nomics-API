package com.applyplugin.cryptocurrencytracker.ui.cryptodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applyplugin.cryptocurrencytracker.R
import com.applyplugin.cryptocurrencytracker.databinding.FragmentCryptoDetailsBinding
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse

class CryptoDetailsFragment : Fragment() {

    private var _binding: FragmentCryptoDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCryptoDetailsBinding.inflate(inflater, container, false)

        return binding.root

    }

}
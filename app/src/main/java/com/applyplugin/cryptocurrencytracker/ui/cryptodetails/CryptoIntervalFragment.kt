package com.applyplugin.cryptocurrencytracker.ui.cryptodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.applyplugin.cryptocurrencytracker.databinding.FragmentCryptoIntervalBinding
import com.applyplugin.cryptocurrencytracker.model.PriceChange
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_BUNDLE

class CryptoIntervalFragment : Fragment() {

    private var _binding: FragmentCryptoIntervalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
            _binding = FragmentCryptoIntervalBinding.inflate(inflater, container, false)

        val args = arguments
        @Suppress("DEPRECATION")
        val bundle: PriceChange = args!!.getParcelable<PriceChange>(CRYPTO_BUNDLE) as PriceChange

        binding.valueVolume.text = bundle.volume
        binding.valueVolChng.text = bundle.volumeChange
        binding.valueVolChngPrcnt.text = bundle.volumeChangePct
        binding.valueMrktCpChng.text = bundle.marketCapChange
        binding.valueMrktCpChngPrcnt.text = bundle.marketCapChangePct

        return binding.root
    }

}
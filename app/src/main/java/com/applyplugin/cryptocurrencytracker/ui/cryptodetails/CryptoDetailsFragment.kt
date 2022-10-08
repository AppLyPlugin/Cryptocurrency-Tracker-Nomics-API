package com.applyplugin.cryptocurrencytracker.ui.cryptodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.size.Precision
import coil.size.Scale
import coil.transform.CircleCropTransformation
import com.applyplugin.cryptocurrencytracker.R
import com.applyplugin.cryptocurrencytracker.databinding.FragmentCryptoDetailsBinding
import com.applyplugin.cryptocurrencytracker.model.CryptoResponse
import com.applyplugin.cryptocurrencytracker.util.Constants
import com.applyplugin.cryptocurrencytracker.bindingadapter.CryptoDetailsBinding

class CryptoDetailsFragment : Fragment() {

    private var _binding: FragmentCryptoDetailsBinding? = null
    private val binding get() = _binding!!

    private val adapter = CryptoDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCryptoDetailsBinding.inflate(inflater, container, false)

        val args = arguments

        @Suppress("DEPRECATION")
        val bundle: CryptoResponse =
            args!!.getParcelable<CryptoResponse>(Constants.CRYPTO_DETAIL) as CryptoResponse

        binding.cryptoRank.text = "RANK#" + bundle.rank
        binding.cryptoStatus.text = bundle.status.toUpperCase()
        binding.cryptoName.text = bundle.name
        binding.cryptoPrice.text = bundle.price
        binding.timeStamp.text = adapter.formatTime(bundle.priceTimestamp) + "\n" +
                            adapter.formatDate(bundle.priceTimestamp)
        binding.cryptoSymbol.text = bundle.symbol.toUpperCase()

        binding.valueHigh.text = bundle.high?.let { adapter.formatRound2Decimals(it) }
        binding.valueHighTmStmp.text = bundle.highTimestamp?.let { adapter.formatDate(it) }
        binding.valueMaxSupply.text = bundle.maxSupply?.let { adapter.formatRound2Decimals(it) }
        binding.valueMarketCap.text = bundle.marketCap?.let { adapter.formatRound2Decimals(it) }
        binding.valueMarketCapPrcnt.text = bundle.marketCapDominance?.let { adapter.formatToPercentage(it)}

        binding.cryptoLogo.apply {
            load(
                "https://cryptologos.cc/logos/${
                    bundle.name.toLowerCase().replace(" ", "-")
                }-${bundle.symbol.toLowerCase()}-logo.png?v=023"
            ){
                crossfade(true)
                placeholder(R.drawable.ic_error_loading_image)
                error(R.drawable.ic_error_loading_image)
                transformations(CircleCropTransformation())
                precision(Precision.EXACT)
                scale(Scale.FILL)
            }
        }

        return binding.root

    }

}
package com.applyplugin.cryptocurrencytracker.ui.cryptodetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.compose.ui.text.toUpperCase
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.applyplugin.cryptocurrencytracker.databinding.ActivityCryptoDetailsBinding
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_BUNDLE
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_DAY1
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_DAY30
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_DAY365
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_DAY7
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_YTD
import com.google.android.material.tabs.TabLayoutMediator

class CryptoDetails : AppCompatActivity() {

    private val args by navArgs<CryptoDetailsArgs>()

    private lateinit var binding: ActivityCryptoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setTitle(args.cryptoDetails.name.toUpperCase())

        val fragments = ArrayList<Fragment>()
        fragments.add(CryptoIntervalFragment())
        fragments.add(CryptoIntervalFragment())
        fragments.add(CryptoIntervalFragment())
        fragments.add(CryptoIntervalFragment())
        fragments.add(CryptoIntervalFragment())

        val titles = ArrayList<String>()
        titles.add(CRYPTO_DETAIL_TITLE_DAY1)
        titles.add(CRYPTO_DETAIL_TITLE_DAY7)
        titles.add(CRYPTO_DETAIL_TITLE_DAY30)
        titles.add(CRYPTO_DETAIL_TITLE_DAY365)
        titles.add(CRYPTO_DETAIL_TITLE_YTD)

        val bundleDetail = Bundle()
        val bundleDay1 = Bundle()
        val bundleDay7 = Bundle()
        val bundleDay30 = Bundle()
        val bundleDay365 = Bundle()
        val bundleYTD = Bundle()

        bundleDetail.putParcelable(CRYPTO_DETAIL, args.cryptoDetails)
        bundleDay1.putParcelable(CRYPTO_BUNDLE, args.cryptoDetails.day1)
        bundleDay7.putParcelable(CRYPTO_BUNDLE, args.cryptoDetails.day7)
        bundleDay30.putParcelable(CRYPTO_BUNDLE, args.cryptoDetails.day30)
        bundleDay365.putParcelable(CRYPTO_BUNDLE, args.cryptoDetails.day365)
        bundleYTD.putParcelable(CRYPTO_BUNDLE, args.cryptoDetails.ytd)

        val detailsBundle = Bundle()
        detailsBundle.putBundle(CRYPTO_DETAIL_TITLE_DAY1, bundleDay1)
        detailsBundle.putBundle(CRYPTO_DETAIL_TITLE_DAY7, bundleDay7)
        detailsBundle.putBundle(CRYPTO_DETAIL_TITLE_DAY30, bundleDay30)
        detailsBundle.putBundle(CRYPTO_DETAIL_TITLE_DAY365, bundleDay365)
        detailsBundle.putBundle(CRYPTO_DETAIL_TITLE_YTD, bundleYTD)

        val pagerAdapter = PagerAdapter(
            detailsBundle,
            fragments,
            titles,
            this
        )

        binding.viewPager.apply {
            adapter = pagerAdapter
        }

        binding.fragmentContainerView.getFragment<CryptoDetailsFragment>().arguments = bundleDetail

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}
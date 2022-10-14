package com.applyplugin.cryptocurrencytracker.ui.cryptodetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.applyplugin.cryptocurrencytracker.MainViewModel
import com.applyplugin.cryptocurrencytracker.R
import com.applyplugin.cryptocurrencytracker.databinding.ActivityCryptoDetailsBinding
import com.applyplugin.cryptocurrencytracker.repository.database.entity.WatchlistEntity
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_BUNDLE
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_DAY1
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_DAY30
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_DAY365
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_DAY7
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_DETAIL_TITLE_YTD
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptoDetails : AppCompatActivity() {

    private val args by navArgs<CryptoDetailsArgs>()
    private val mainViewModel: MainViewModel by viewModels()

    private var cryptoSaved = false
    private var savedCryptoID = 0

    private lateinit var binding: ActivityCryptoDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCryptoDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.setTitle(args.cryptoDetails.name.uppercase())

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        val menuItem = menu?.findItem(R.id.add_to_watchlist_menu)
        checkWatchList(menuItem!!)

        return true
    }

    private fun checkWatchList(menuItem: MenuItem) {

        mainViewModel.readWatchlist.observe(this) { watchlistEntity ->
            try {
                for (currentCrypto in watchlistEntity) {
                    if (currentCrypto.id == args.cryptoDetails.id) {
                        changeMenuItemColor(menuItem, R.color.watchlist)
                        cryptoSaved = true
                        break
                    } else {
                        changeMenuItemColor(menuItem, R.color.black)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.add_to_watchlist_menu && !cryptoSaved) {
            addToWatchlist(item)
        } else if (item.itemId == R.id.add_to_watchlist_menu && cryptoSaved) {
            removedFromWatchlist(item)
        }

        return super.onOptionsItemSelected(item)
    }

    private fun addToWatchlist(item: MenuItem) {

        val watchlistEntity =
            WatchlistEntity(
                args.cryptoDetails
            )

        mainViewModel.addCryptoToWatchlist(watchlistEntity)
        changeMenuItemColor(item, R.color.watchlist)
        showSnackBar("Crypto Added to Watchlist")
        cryptoSaved = true
    }

    private fun removedFromWatchlist(item: MenuItem) {
        val watchlistEntity =
            WatchlistEntity(
                args.cryptoDetails
            )
        mainViewModel.deleteCryptoFromWatchlist(watchlistEntity)
        changeMenuItemColor(item, R.color.black)
        showSnackBar("Removed from Watchlist")
        cryptoSaved = false
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(
            binding.detailsLayout,
            message,
            Snackbar.LENGTH_LONG
        ).setAction("Okay") {}
            .show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon?.setTint(ContextCompat.getColor(this, color))
    }


}
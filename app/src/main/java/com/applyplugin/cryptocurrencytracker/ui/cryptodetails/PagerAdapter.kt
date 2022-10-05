@file:Suppress("DEPRECATION")

package com.applyplugin.cryptocurrencytracker.ui.cryptodetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.applyplugin.cryptocurrencytracker.util.Constants.Companion.CRYPTO_BUNDLE

class PagerAdapter(
    private val bundle: Bundle,
    private val fragment: ArrayList<Fragment>,
    private val titles: ArrayList<String>,
    fragmentActivity: FragmentActivity
): FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return fragment.size
    }

    override fun createFragment(position: Int): Fragment {
        fragment[position].arguments = bundle.getBundle(titles[position])
        return fragment[position]
    }

}
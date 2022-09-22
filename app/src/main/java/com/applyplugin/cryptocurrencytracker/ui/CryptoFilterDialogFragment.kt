package com.applyplugin.cryptocurrencytracker.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.navigation.fragment.findNavController
import com.applyplugin.cryptocurrencytracker.databinding.FragmentCryptoFilterDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import java.util.*

@Suppress("DEPRECATION")
class CryptoFilerDialogFragment : BottomSheetDialogFragment() {

    private lateinit var cryptoViewModel: CryptoViewModel //by viewModels()

    private var _binding: FragmentCryptoFilterDialogBinding? = null
    private val binding get() = _binding!!

    private var selectedFilter = ""
    private var selectedFilterId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cryptoViewModel = ViewModelProvider(requireActivity()).get(CryptoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding =  FragmentCryptoFilterDialogBinding.inflate(inflater, container, false)

        cryptoViewModel.readFilter.asLiveData().observe(viewLifecycleOwner) { value ->
            selectedFilter = value.filter
            updateChip(value.filterID, binding.chipFilterGroup)

        }

        binding.chipFilterGroup.setOnCheckedChangeListener{ chipGroup, selectedChipId ->
            val chip = chipGroup.findViewById<Chip>(selectedChipId)
            val selectedMealType = chip.text.toString().toLowerCase(Locale.ROOT)
            selectedFilter = selectedMealType
            selectedFilterId = selectedChipId

        }

        binding.applyFilter.setOnClickListener {

            cryptoViewModel.saveFilter(
                selectedFilter,
                selectedFilterId,
            )

            val action = CryptoFilerDialogFragmentDirections.actionCryptoFilterDialogFragmentToCryptoFragment(true)
            findNavController().navigate(action)
        }


        return binding.root
    }

    private fun updateChip(chipId: Int, chipGroup: ChipGroup) {
        if (chipId != 0) {
            try {
                chipGroup.findViewById<Chip>(chipId).isChecked = true
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package com.softcross.onepiece.presentation.locations

import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.core.common.extension.gone
import com.softcross.onepiece.core.common.extension.visible
import com.softcross.onepiece.core.data.entity.LocationEntity
import com.softcross.onepiece.databinding.FragmentLocationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocationsFragment : Fragment(R.layout.fragment_locations) {

    private val viewModel: LocationsViewModel by viewModels()
    private val binding: FragmentLocationsBinding by viewBinding(FragmentLocationsBinding::bind)
    private val adapter = LocationsAdapter().apply {
        setItemClickListener {
            val item = getItemAtPosition(it)
            findNavController().navigate(
                LocationsFragmentDirections.locationsToSubLocation(
                    item.id,
                    item.locationName
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllLocations()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
    }

    private fun observeUI() {
        viewModel.locationsUiState.observe(viewLifecycleOwner) { locationsUiState ->
            when (locationsUiState) {
                is LocationsUiState.Loading -> contentVisible(false)
                is LocationsUiState.Error -> handleError(locationsUiState.errorMessage)
                is LocationsUiState.Success -> handleSuccess(locationsUiState.data)
            }
        }
    }

    private fun handleSuccess(locationList: List<LocationEntity>) {
        adapter.updateItems(locationList)
        binding.rvLocations.adapter = adapter
        contentVisible(true)
    }

    private fun handleError(errorMessage: String) {
        with(binding) {
            viewErrorLocations.visible()
            viewErrorLocations.setRetryOnClick { viewModel.getAllLocations() }
            viewLoadingLocations.gone()
            rvLocations.gone()
            viewErrorLocations.setErrorMessage(errorMessage)
        }
    }

    private fun contentVisible(isVisible: Boolean) {
        with(binding) {
            viewErrorLocations.gone()
            viewLoadingLocations.isVisible = !isVisible
            rvLocations.isVisible = isVisible
        }
    }
}
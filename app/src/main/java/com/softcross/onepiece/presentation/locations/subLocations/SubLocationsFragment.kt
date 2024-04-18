package com.softcross.onepiece.presentation.locations.subLocations

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.core.common.extension.gone
import com.softcross.onepiece.core.common.extension.visible
import com.softcross.onepiece.databinding.FragmentSubLocationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SubLocationsFragment : Fragment(R.layout.fragment_sub_locations) {

    private val viewModel: SubLocationsViewModel by viewModels()
    private val binding: FragmentSubLocationsBinding by viewBinding(FragmentSubLocationsBinding::bind)
    private val args: SubLocationsFragmentArgs by navArgs()
    private val adapter = SubLocationAdapter().apply {
        setOnFavoriteClickListener {
            viewModel.changeSubLocationFavoriteState(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllSubLocationsByLocation(args.locationID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
    }

    private fun observeUI() {
        viewModel.subLocationsUiState.observe(viewLifecycleOwner) { subLocationUiState ->
            when (subLocationUiState) {
                is SubLocationsUiState.Loading -> contentVisible(false)
                is SubLocationsUiState.Error -> handleError(subLocationUiState.errorMessage)
                is SubLocationsUiState.Success -> handleSuccess(subLocationUiState.data)
            }
        }
    }

    private fun handleError(errorMessage: String) {
        with(binding) {
            viewErrorSubLocations.visible()
            viewLoadingSubLocations.gone()
            rvSubLocations.gone()
            viewToolbarSubLocation.gone()
            viewErrorSubLocations.setRetryOnClick { viewModel.getAllSubLocationsByLocation(args.locationID) }
            viewErrorSubLocations.setErrorMessage(errorMessage)
        }
    }

    private fun handleSuccess(subLocationList: List<SubLocationUiItem>) {
        adapter.updateItems(subLocationList)
        binding.viewToolbarSubLocation.setTitle(args.locationName)
        binding.viewToolbarSubLocation.backClickListener { findNavController().navigate(R.id.sub_location_to_location) }
        binding.rvSubLocations.adapter = adapter
        contentVisible(true)
    }

    private fun contentVisible(isVisible: Boolean) {
        with(binding) {
            viewErrorSubLocations.gone()
            viewLoadingSubLocations.isVisible = !isVisible
            rvSubLocations.isVisible = isVisible
            viewToolbarSubLocation.isVisible = isVisible
        }
    }
}
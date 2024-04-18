package com.softcross.onepiece.presentation.occupations

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.core.common.extension.gone
import com.softcross.onepiece.core.common.extension.visible
import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.databinding.FragmentOccupationsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OccupationsFragment : Fragment(R.layout.fragment_occupations) {

    private val viewModel: OccupationsViewModel by viewModels()
    private val binding: FragmentOccupationsBinding by viewBinding(FragmentOccupationsBinding::bind)
    private val adapter = OccupationsAdapter().apply {
        setOnFavoriteClickListener {
            viewModel.changeOccupationFavoriteState(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getALlOccupations()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUi()
        binding.fabToFav.setOnClickListener {
            findNavController().navigate(R.id.occupations_to_favorites)
        }
    }

    private fun observeUi() {
        viewModel.occupationUiState.observe(viewLifecycleOwner) { occupationUiState ->
            when (occupationUiState) {
                is OccupationsUiState.Loading -> contentVisible(false)
                is OccupationsUiState.Error -> handleError(occupationUiState.errorMessage)
                is OccupationsUiState.Success -> handleSuccess(occupationUiState.occupationList)
            }
        }
    }

    private fun handleSuccess(occupationList: List<OccupationUiItem>) {
        adapter.updateItems(occupationList)
        binding.rvOccupations.adapter = adapter
        contentVisible(true)
    }

    private fun handleError(errorMessage: String) {
        with(binding) {
            viewErrorOccupations.visible()
            viewErrorOccupations.setRetryOnClick { viewModel.getALlOccupations() }
            viewLoadingOccupations.gone()
            rvOccupations.gone()
            viewErrorOccupations.setErrorMessage(errorMessage)
        }
    }

    private fun contentVisible(isVisible: Boolean) {
        with(binding) {
            viewErrorOccupations.gone()
            viewLoadingOccupations.isVisible = !isVisible
            rvOccupations.isVisible = isVisible
        }
    }

}
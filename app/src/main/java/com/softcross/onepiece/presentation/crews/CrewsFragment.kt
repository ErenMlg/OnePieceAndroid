package com.softcross.onepiece.presentation.crews

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.databinding.FragmentCrewsBinding
import com.softcross.onepiece.core.common.extension.gone
import com.softcross.onepiece.core.common.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CrewsFragment : Fragment(R.layout.fragment_crews) {

    private val binding by viewBinding(FragmentCrewsBinding::bind)
    private val viewModel: CrewsViewModel by viewModels()
    private val adapter = CrewsAdapter().apply {
        setItemClickListener {
            findNavController().navigate(
                CrewsFragmentDirections.crewToDetail(
                    getItemAtPosition(it).id
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllCrews()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
    }

    private fun observeUI() {
        viewModel.crewsUiState.observe(viewLifecycleOwner) { crewListUiState ->
            when (crewListUiState) {
                is CrewListUiState.Loading -> {
                    contentVisible(false)
                }

                is CrewListUiState.Error -> {
                    handleError(crewListUiState.errorMessage)
                }

                is CrewListUiState.Success -> {
                    handleSuccess(crewListUiState.data)
                }
            }
        }
    }

    private fun handleSuccess(crewList: List<CrewUiItem>) {
        adapter.updateItems(crewList)
        binding.rvCrews.adapter = adapter
        contentVisible(true)
    }

    private fun handleError(errorMessage: String) {
        with(binding) {
            viewLoadingCrews.gone()
            rvCrews.gone()
            viewErrorCrews.visible()
            viewErrorCrews.setErrorMessage(errorMessage)
        }
    }

    private fun contentVisible(isVisible: Boolean) {
        with(binding) {
            viewLoadingCrews.isVisible = !isVisible
            rvCrews.isVisible = isVisible
            viewErrorCrews.gone()
        }
    }


}
package com.softcross.onepiece.presentation.devilFruits

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.core.view.isVisible
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.core.common.extension.gone
import com.softcross.onepiece.core.common.extension.visible
import com.softcross.onepiece.core.data.entity.DevilFruitEntity
import com.softcross.onepiece.databinding.FragmentDevilFruitsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DevilFruitsFragment : Fragment(R.layout.fragment_devil_fruits) {

    private val viewModel: DevilFruitsViewModel by viewModels()
    private val binding: FragmentDevilFruitsBinding by viewBinding(FragmentDevilFruitsBinding::bind)
    private val adapter = DevilFruitsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllDevilFruits()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUi()
    }

    private fun observeUi() {
        viewModel.devilFruitsUiState.observe(viewLifecycleOwner) { devilFruitUiState ->
            when (devilFruitUiState) {
                is DevilFruitUiState.Loading -> {
                    contentVisible(false)
                }

                is DevilFruitUiState.Error -> {
                    handleError(devilFruitUiState.errorMessage)
                }

                is DevilFruitUiState.Success -> {
                    handleSuccess(devilFruitUiState.data)
                }
            }
        }
    }

    private fun handleSuccess(devilFruits: List<DevilFruitEntity>) {
        adapter.updateItems(devilFruits)
        binding.rvDevilFruits.adapter = adapter
        contentVisible(true)
    }

    private fun handleError(errorMessage: String) {
        with(binding) {
            viewErrorDevilFruit.visible()
            viewLoadingDevilFruit.gone()
            rvDevilFruits.gone()
            viewErrorDevilFruit.setErrorMessage(errorMessage)
        }
    }

    private fun contentVisible(isVisible: Boolean) {
        with(binding) {
            viewErrorDevilFruit.gone()
            viewLoadingDevilFruit.isVisible = !isVisible
            rvDevilFruits.isVisible = isVisible
        }
    }

}
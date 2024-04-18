package com.softcross.onepiece.presentation.favorite

import androidx.fragment.app.viewModels
import android.os.Bundle
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
import com.softcross.onepiece.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(R.layout.fragment_favorites) {

    private val viewModel: FavoritesViewModel by viewModels()
    private val binding: FragmentFavoritesBinding by viewBinding(FragmentFavoritesBinding::bind)
    private val adapter = FavoritesAdapter().apply {
        setOnDeleteFavoriteClickListener {

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
        binding.viewToolbarFavorites.setTitle("Favorites")
        binding.viewToolbarFavorites.setOnClickListener {
            findNavController().navigate(R.id.favorites_to_characters)
        }
    }

    private fun observeUI() {
        viewModel.favoritesScreenUiState.observe(viewLifecycleOwner) { favoritesUiState ->
            when (favoritesUiState) {
                is FavoritesUiState.Error -> {
                    handleError(favoritesUiState.errorMessage)
                }

                is FavoritesUiState.Loading -> {
                    contentVisible(false)
                }

                is FavoritesUiState.Success -> {
                    handleSuccess(favoritesUiState.data)
                }
            }
        }
    }

    private fun handleSuccess(favoriteList: List<FavoritesUiItem>) {
        adapter.updateFavoriteList(favoriteList)
        binding.rvFavorites.adapter = adapter
        contentVisible(true)
    }

    private fun contentVisible(isVisible: Boolean) {
        with(binding) {
            viewErrorLayoutFavorites.gone()
            viewLoadingFavorites.isVisible = !isVisible
            rvFavorites.isVisible = isVisible
        }
    }

    private fun handleError(errorMessage: String) {
        with(binding) {
            viewErrorLayoutFavorites.visible()
            viewLoadingFavorites.gone()
            rvFavorites.gone()
            viewErrorLayoutFavorites.setRetryOnClick { viewModel.getAllFavorites() }
            viewErrorLayoutFavorites.setErrorMessage(errorMessage)
        }
    }


}
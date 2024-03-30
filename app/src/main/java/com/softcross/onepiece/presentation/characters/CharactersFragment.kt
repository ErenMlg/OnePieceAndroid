package com.softcross.onepiece.presentation.characters

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.GridLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.databinding.FragmentCharactersBinding
import com.softcross.onepiece.presentation.characters.adapter.CharacterListAdapter
import com.softcross.onepiece.presentation.util.gone
import com.softcross.onepiece.presentation.util.visible
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()
    private val adapter = CharacterListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllCharacters()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
    }

    private fun observeUI() {
        viewModel.characterScreenUiState.observe(viewLifecycleOwner) { state ->
            when {
                state.isLoading -> contentVisible(false)

                state.isError -> handleError(state.errorMessage)

                else -> handleSuccessResponse(state.uiItems)
            }
        }
    }

    private fun handleError(errorMessage: String?) {
        binding.apply {
            txtError.text = errorMessage
            errorLayout.visible()
            viewLoading.gone()
            rvCharacters.gone()
        }

    }

    private fun handleSuccessResponse(uiItems: List<OnePieceItem>) {
        contentVisible(true)
        adapter.updateItems(uiItems)
        with(binding) {
            rvCharacters.adapter = adapter
            rvCharacters.layoutManager = GridLayoutManager(requireContext(), 2).apply {
                spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return when (position) {
                            0 -> 2
                            else -> 1
                        }
                    }
                }
            }
        }
    }

    private fun contentVisible(isVisible: Boolean) {
        binding.apply {
            viewLoading.isVisible = !isVisible
            rvCharacters.isVisible = isVisible
            errorLayout.gone()
        }
    }
}
package com.softcross.onepiece.presentation.characters

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.databinding.FragmentCharactersBinding
import com.softcross.onepiece.presentation.characters.adapter.CharacterListAdapter
import com.softcross.onepiece.core.common.extension.gone
import com.softcross.onepiece.core.common.extension.visible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()
    private val adapter = CharacterListAdapter().apply {
        setOnCharacterItemClickListener {
            val id =
                (getItemAtPosition(it) as CharacterListItem.CharacterItem).characterListUiItem.id
            findNavController().navigate(CharactersFragmentDirections.characterToDetail(id))
        }

        setOnVideoItemClickListener {
            findNavController().navigate(CharactersFragmentDirections.charactersToVideo(VIDEO_URL))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllCharacters()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
        binding.fabToFav.setOnClickListener {
            findNavController().navigate(R.id.characters_to_favorite)
        }
    }

    private fun observeUI() {
        viewModel.characterScreenUiState.observe(viewLifecycleOwner) { characterListUiState ->
            when {
                characterListUiState.isLoading -> contentVisible(false)

                characterListUiState.isError -> handleError(characterListUiState.errorMessage)

                else -> handleSuccessResponse(characterListUiState.uiItems)
            }
        }
    }

    private fun handleError(errorMessage: String?) {
        binding.apply {
            viewErrorLayoutCharacters.setErrorMessage(errorMessage ?: "Error")
            viewErrorLayoutCharacters.visible()
            viewErrorLayoutCharacters.setRetryOnClick { viewModel.getAllCharacters() }
            viewLoadingCharacters.gone()
            rvCharacters.gone()
        }
    }

    private fun handleSuccessResponse(uiItems: List<CharacterListItem>) {
        adapter.updateItems(uiItems)
        binding.apply {
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
        contentVisible(true)
    }

    private fun contentVisible(isVisible: Boolean) {
        binding.apply {
            viewLoadingCharacters.isVisible = !isVisible
            rvCharacters.isVisible = isVisible
            viewErrorLayoutCharacters.gone()
        }
    }

    companion object {
        const val VIDEO_URL =
            "https://video.fist7-1.fna.fbcdn.net/v/t42.1790-2/435543024_763035555929802_1700128639776742492_n.mp4?_nc_cat=111&ccb=1-7&_nc_sid=55d0d3&efg=eyJ2ZW5jb2RlX3RhZyI6InN2ZV9zZCIsInZpZGVvX2lkIjoxMDk3MzY5OTA4MjYxNDY0fQ%3D%3D&_nc_ohc=xIf4wAmGunwAb6cKvu9&_nc_rml=0&_nc_ht=video.fist7-1.fna&oh=00_AfBHBx0aDaWtE7pSX8TJ4ipwhxnrglcLy8Mc38ulGDG7RA&oe=6626F69E"
    }
}
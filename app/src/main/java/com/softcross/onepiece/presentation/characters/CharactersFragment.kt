package com.softcross.onepiece.presentation.characters

import android.os.Bundle
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
import com.softcross.onepiece.presentation.util.gone
import com.softcross.onepiece.presentation.util.visible
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllCharacters()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
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
            errorLayout.setErrorMessage(errorMessage ?: "Error")
            errorLayout.visible()
            viewLoading.gone()
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
            viewLoading.isVisible = !isVisible
            rvCharacters.isVisible = isVisible
            errorLayout.gone()
        }
    }
}
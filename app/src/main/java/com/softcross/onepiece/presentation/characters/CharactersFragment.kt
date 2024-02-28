package com.softcross.onepiece.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.GridLayout
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.databinding.FragmentCharactersBinding
import com.softcross.onepiece.presentation.characters.adapter.CharacterListAdapter
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
                state.isLoading -> handleLoading()

                state.isError -> handleError(state.errorMessage)

                else -> handleSuccessResponse(state.uiItems)
            }
        }
    }

    private fun handleLoading() {
        with(binding) {
            txtLoading.visibility = View.VISIBLE
            pbLoading.visibility = View.VISIBLE
        }

    }

    private fun handleError(errorMessage: String?) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
    }

    private fun handleSuccessResponse(uiItems: List<OnePieceItem>) {
        adapter.updateItems(uiItems)
        with(binding) {
            txtLoading.visibility = View.INVISIBLE
            pbLoading.visibility = View.INVISIBLE
            rvCharacters.adapter = adapter
            rvCharacters.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}
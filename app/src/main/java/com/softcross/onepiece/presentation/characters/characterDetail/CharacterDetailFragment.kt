package com.softcross.onepiece.presentation.characters.characterDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.databinding.FragmentCharacterDetailBinding
import com.softcross.onepiece.core.common.extension.gone
import com.softcross.onepiece.core.common.extension.loadOnBitmap
import com.softcross.onepiece.core.common.extension.visible
import com.softcross.onepiece.core.data.modal.Character
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private val args: CharacterDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getCharacter(args.uuid)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
    }

    private fun observeUI() {
        viewModel.characterDetailScreenUiState.observe(viewLifecycleOwner) { characterDetailUiState ->
            when (characterDetailUiState) {
                is CharacterDetailUiState.Loading -> {
                    contentVisible(false)
                }

                is CharacterDetailUiState.Error -> {
                    handleError(characterDetailUiState.errorMessage)
                }

                is CharacterDetailUiState.Success -> {
                    handleSuccess(characterDetailUiState.data, characterDetailUiState.isFavorite)
                }
            }
        }
    }

    private fun handleSuccess(character: Character, isFavorite: Boolean) {
        binding.apply {
            viewCharacterDetailPicture.loadOnBitmap(character.characterPictureURL)
            onePieceUiToolbarComponent.setTitle(character.characterName)
            viewInfoStatus.setInfo(character.characterStatus)
            viewInfoCrew.setInfo(character.characterCrew)
            viewInfoDevilFruit.setInfo(if (character.characterDevilFruit == "") "None" else character.characterDevilFruit)
            viewInfoOrigin.setInfo(character.characterOrigin)
            viewInfoOccupation.setInfo(character.characterOccupation)
            viewInfoAbilities.setInfo(character.characterAbilities)
            viewInfoDiamond.setInfo(character.characterBounty)
            onePieceUiToolbarComponent.backClickListener { findNavController().navigate(R.id.detail_to_all_characters) }
            ivFav.setImageResource(if (isFavorite) R.drawable.ic_remove_favorite else R.drawable.ic_add_favorite)
            ivFav.setOnClickListener {
                viewModel.changeCharacterFavoriteState(character, isFavorite)
            }
        }
        contentVisible(true)
    }

    private fun handleError(errorMessage: String?) {
        binding.apply {
            viewErrorCharacterDetail.setErrorMessage(errorMessage ?: "Error")
            viewErrorCharacterDetail.setRetryOnClick { viewModel.getCharacter(args.uuid) }
            viewErrorCharacterDetail.visible()
            viewLoadingCharacterDetail.gone()
            onePieceUiToolbarComponent.gone()
            viewCharacterDetailPicture.gone()
            characterDetailView.gone()
        }
    }

    private fun contentVisible(isVisible: Boolean) {
        binding.apply {
            onePieceUiToolbarComponent.isVisible = isVisible
            viewCharacterDetailPicture.isVisible = isVisible
            characterDetailView.isVisible = isVisible
            viewLoadingCharacterDetail.isVisible = !isVisible
            viewErrorCharacterDetail.gone()
        }
    }

}
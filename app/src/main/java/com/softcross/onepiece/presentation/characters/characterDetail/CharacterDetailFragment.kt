package com.softcross.onepiece.presentation.characters.characterDetail

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toBitmap
import androidx.core.graphics.drawable.toBitmapOrNull
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.databinding.FragmentCharacterDetailBinding
import com.softcross.onepiece.presentation.util.gone
import com.softcross.onepiece.presentation.util.visible
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class CharacterDetailFragment : Fragment(R.layout.fragment_character_detail) {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private val binding by viewBinding(FragmentCharacterDetailBinding::bind)
    private val args: CharacterDetailFragmentArgs by navArgs()
    private val imageTarget = object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            binding.viewCharacterDetailPicture.setBitmap(bitmap)
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            binding.viewCharacterDetailPicture.setBitmap(errorDrawable?.toBitmapOrNull())
            println(e)
        }


        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            binding.viewCharacterDetailPicture.setBitmap(placeHolderDrawable?.toBitmapOrNull())
        }
    }

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
                    Picasso.get().load(characterDetailUiState.data.picture).into(imageTarget)
                    handleSuccess(characterDetailUiState.data)
                }
            }
        }
    }

    private fun handleSuccess(detailUiItem: CharacterDetailUiItem) {
        binding.apply {
            onePieceUiToolbarComponent.setOnTitle(detailUiItem.name)
            viewInfoStatus.setInfo(detailUiItem.status)
            viewInfoCrew.setInfo(detailUiItem.crew)
            viewInfoDevilFruit.setInfo(detailUiItem.devilFruit)
            viewInfoOrigin.setInfo(detailUiItem.origin)
            viewInfoOccupation.setInfo(detailUiItem.occupation)
            viewInfoAbilities.setInfo(detailUiItem.abilities)
            viewInfoDiamond.setInfo(detailUiItem.bounty)
        }
        contentVisible(true)

    }

    private fun handleError(errorMessage: String?) {
        binding.apply {
            viewError.setErrorMessage(errorMessage ?: "Error")
            viewError.visible()
            viewLoading.gone()
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
            viewLoading.isVisible = !isVisible
            viewError.gone()
        }
    }

}
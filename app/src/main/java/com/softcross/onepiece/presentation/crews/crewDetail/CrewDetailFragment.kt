package com.softcross.onepiece.presentation.crews.crewDetail

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.core.common.extension.gone
import com.softcross.onepiece.core.common.extension.loadOnBitmap
import com.softcross.onepiece.core.common.extension.visible
import com.softcross.onepiece.core.data.modal.Crew
import com.softcross.onepiece.databinding.FragmentCrewDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CrewDetailFragment : Fragment(R.layout.fragment_crew_detail) {

    private val binding by viewBinding(FragmentCrewDetailBinding::bind)
    private val viewModel: CrewDetailViewModel by viewModels()
    private val args: CrewDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getSingleCrew(args.uuid)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUI()
    }

    private fun observeUI() {
        viewModel.crewDetailUiState.observe(viewLifecycleOwner) { crewDetailUiState ->
            when (crewDetailUiState) {
                is CrewDetailUiState.Loading -> {
                    contentVisible(false)
                }

                is CrewDetailUiState.Error -> {
                    handleError(crewDetailUiState.errorMessage)
                }

                is CrewDetailUiState.Success -> {
                    handleSuccess(crewDetailUiState.data, crewDetailUiState.isFavorite)
                }
            }
        }
    }

    private fun handleSuccess(crew: Crew, isFavorite: Boolean) {
        with(binding) {
            crewFlagDetailBox.loadOnBitmap(crew.crewFlagURL)
            viewInfoShip.setInfo(crew.crewMainShip)
            viewInfoBounty.setInfo(crew.crewTotalBounty)
            viewCrewDetailToolbar.setTitle(crew.crewName)
            ivCrewFav.setImageResource(if (isFavorite) R.drawable.ic_remove_favorite else R.drawable.ic_add_favorite)
            ivCrewFav.setOnClickListener { viewModel.changeCrewFavoriteState(crew, isFavorite) }
            viewCrewDetailToolbar.backClickListener {
                findNavController().navigate(R.id.detail_to_all_crews)
            }
        }
        contentVisible(true)
    }

    private fun handleError(errorMessage: String) {
        with(binding) {
            viewErrorCrewDetail.visible()
            viewErrorCrewDetail.setRetryOnClick { viewModel.getSingleCrew(args.uuid) }
            viewErrorCrewDetail.setErrorMessage(errorMessage)
            viewLoadingCrewDetail.gone()
            crewDetailBox.gone()
            crewFlagDetailBox.gone()
        }
    }

    private fun contentVisible(isVisible: Boolean) {
        with(binding) {
            viewLoadingCrewDetail.isVisible = !isVisible
            crewDetailBox.isVisible = isVisible
            crewFlagDetailBox.isVisible = isVisible
            viewErrorCrewDetail.gone()
        }
    }
}
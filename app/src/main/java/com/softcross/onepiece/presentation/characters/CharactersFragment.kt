package com.softcross.onepiece.presentation.characters

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.delegate.viewBinding
import com.softcross.onepiece.databinding.FragmentCharactersBinding

class CharactersFragment : Fragment(R.layout.fragment_characters) {

    private val binding by viewBinding(FragmentCharactersBinding::bind)
    private val viewModel: CharactersViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
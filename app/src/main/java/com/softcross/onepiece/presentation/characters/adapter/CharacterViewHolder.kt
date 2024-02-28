package com.softcross.onepiece.presentation.characters.adapter

import android.content.res.Resources
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.softcross.onepiece.R
import com.softcross.onepiece.databinding.AdapterCharacterItemBinding
import com.softcross.onepiece.presentation.characters.OnePieceItem
import com.squareup.picasso.Picasso

class CharacterViewHolder(private val binding: AdapterCharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: OnePieceItem.CharacterItem) {
        with(binding) {
            txtCharacterName.text = item.uiCharacterItem.name
            txtCrew.text =
                itemView.context.getString(R.string.characterUiCrew, item.uiCharacterItem.crew)
            txtOrigin.text =
                itemView.context.getString(R.string.characterUiOrigin, item.uiCharacterItem.origin)
            Picasso.get().load(item.uiCharacterItem.picture).into(ivCharacter)
        }
    }

}
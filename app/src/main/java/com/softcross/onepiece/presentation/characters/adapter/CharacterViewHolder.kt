package com.softcross.onepiece.presentation.characters.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.text.Layout.Directions
import androidx.recyclerview.widget.RecyclerView
import com.softcross.onepiece.databinding.ItemCharacterAdapterBinding
import com.softcross.onepiece.presentation.characters.CharacterListItem
import com.softcross.onepiece.presentation.characters.CharactersFragmentDirections
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class CharacterViewHolder(
    private val binding: ItemCharacterAdapterBinding,
    private val onCharacterItemClickListener: ((Int) -> Unit)?
) :
    RecyclerView.ViewHolder(binding.root) {

    private val imageTarget = object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            binding.bannerView.setBitmap(bitmap)
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        }


        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
    }

    init {
        binding.root.setOnClickListener {
            onCharacterItemClickListener?.invoke(adapterPosition)
        }
    }

    fun bind(item: CharacterListItem.CharacterItem) {
        with(binding) {
            Picasso.get().load(item.characterListUiItem.picture).resize(200, 200).into(imageTarget)
            bannerView.setTexts(item.characterListUiItem.name, item.characterListUiItem.bounty)
        }
    }
}
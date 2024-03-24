package com.softcross.onepiece.presentation.characters.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.softcross.onepiece.databinding.AdapterCharacterItemBinding
import com.softcross.onepiece.presentation.characters.OnePieceItem
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class CharacterViewHolder(private val binding: AdapterCharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private val imageTarget = object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            binding.bannerView.setBitmap(bitmap)
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
        }


        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
    }

    fun bind(item: OnePieceItem.CharacterItem) {
        with(binding) {
            Picasso.get().load(item.uiCharacterItem.picture).resize(200, 200).into(imageTarget)
            bannerView.setTexts(item.uiCharacterItem.name,item.uiCharacterItem.bounty)
        }
    }
}
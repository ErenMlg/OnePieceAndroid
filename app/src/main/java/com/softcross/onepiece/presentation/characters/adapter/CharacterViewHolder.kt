package com.softcross.onepiece.presentation.characters.adapter

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.softcross.onepiece.R
import com.softcross.onepiece.databinding.AdapterCharacterItemBinding
import com.softcross.onepiece.presentation.characters.OnePieceItem
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

class CharacterViewHolder(private val binding: AdapterCharacterItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    private var characterBitmap: Bitmap? = null
    private val imageTarget = object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            characterBitmap = bitmap
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}

    }

    fun bind(item: OnePieceItem.CharacterItem) {
        with(binding) {
            Picasso.get().load(item.uiCharacterItem.picture).resize(200,200).into(imageTarget)
            bannerView.bindCharacter(characterBitmap, item.uiCharacterItem.name, "1.000.000")
        }
    }


}
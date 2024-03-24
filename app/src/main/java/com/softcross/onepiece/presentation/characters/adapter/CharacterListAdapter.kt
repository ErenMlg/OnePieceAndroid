package com.softcross.onepiece.presentation.characters.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softcross.onepiece.core.common.extension.inflateAdapterItem
import com.softcross.onepiece.databinding.AdapterCharacterItemBinding
import com.softcross.onepiece.databinding.AdapterVideoItemBinding
import com.softcross.onepiece.presentation.characters.OnePieceItem
import com.squareup.picasso.Picasso

@SuppressLint("NotifyDataSetChanged")
class CharacterListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val items = mutableListOf<OnePieceItem>()

    fun updateItems(items: List<OnePieceItem>) {
        with(this.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    private fun getItemAtPosition(position: Int) = items.getOrNull(position)

    override fun getItemViewType(position: Int): Int {
        return when (getItemAtPosition(position)) {
            is OnePieceItem.VideoItem -> VIDEO_ITEM_VIEW_TYPE
            is OnePieceItem.CharacterItem -> LIST_ITEM_VIEW_TYPE
            else -> INVALID_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIDEO_ITEM_VIEW_TYPE -> {
                VideoViewHolder(
                    parent.inflateAdapterItem(AdapterVideoItemBinding::inflate)
                )
            }

            LIST_ITEM_VIEW_TYPE -> {
                CharacterViewHolder(
                    parent.inflateAdapterItem(AdapterCharacterItemBinding::inflate)
                )
            }

            else -> throw Exception("Can not be constructed view holder with type:$viewType")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is OnePieceItem.VideoItem -> {
                (holder as VideoViewHolder).bind()
            }

            is OnePieceItem.CharacterItem -> {

                (holder as CharacterViewHolder).bind(item)
            }
        }
    }

    companion object {
        private const val VIDEO_ITEM_VIEW_TYPE = 0
        private const val LIST_ITEM_VIEW_TYPE = 1
        private const val INVALID_VIEW_TYPE = -1
    }


}


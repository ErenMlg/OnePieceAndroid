package com.softcross.onepiece.presentation.characters.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softcross.onepiece.core.common.extension.inflateAdapterItem
import com.softcross.onepiece.databinding.ItemCharacterAdapterBinding
import com.softcross.onepiece.databinding.ItemVideoAdapterBinding
import com.softcross.onepiece.presentation.characters.CharacterListItem

class CharacterListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var onVideoItemClickListener: (() -> Unit)? = null

    private var onCharacterItemClickListener: ((Int) -> Unit)? = null
    fun setOnVideoItemClickListener(onVideoItemClickListener: (() -> Unit)?) {
        this.onVideoItemClickListener = onVideoItemClickListener
    }

    fun setOnCharacterItemClickListener(onCharacterItemClickListener: ((Int) -> Unit)?) {
        this.onCharacterItemClickListener = onCharacterItemClickListener
    }

    private val items = mutableListOf<CharacterListItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<CharacterListItem>) {
        with(this.items) {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    fun getItemAtPosition(position: Int) = items.getOrNull(position)

    override fun getItemViewType(position: Int): Int {
        return when (getItemAtPosition(position)) {
            is CharacterListItem.VideoItem -> VIDEO_ITEM_VIEW_TYPE
            is CharacterListItem.CharacterItem -> LIST_ITEM_VIEW_TYPE
            else -> INVALID_VIEW_TYPE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIDEO_ITEM_VIEW_TYPE -> {
                VideoViewHolder(
                    parent.inflateAdapterItem(ItemVideoAdapterBinding::inflate)
                )
            }

            LIST_ITEM_VIEW_TYPE -> {
                CharacterViewHolder(
                    parent.inflateAdapterItem(ItemCharacterAdapterBinding::inflate),
                    onCharacterItemClickListener
                )
            }

            else -> throw Exception("Can not be constructed view holder with type:$viewType")
        }
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = items[position]) {
            is CharacterListItem.VideoItem -> {
                (holder as VideoViewHolder).bind()
            }

            is CharacterListItem.CharacterItem -> {

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


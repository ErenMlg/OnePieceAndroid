package com.softcross.onepiece.presentation.devilFruits

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.extension.loadOnBitmap
import com.softcross.onepiece.core.common.extension.mapExtensions.toDevilFruit
import com.softcross.onepiece.core.data.modal.DevilFruit
import com.softcross.onepiece.databinding.ItemDevilFruitsAdapterBinding

class DevilFruitsAdapter : RecyclerView.Adapter<DevilFruitsAdapter.DevilFruitsViewHolder>() {

    private var onFavoriteClickListener: ((devilFruit: DevilFruitUiItem) -> Unit)? = null

    fun setOnFavoriteClickListener(onFavoriteClickListener: ((devilFruit: DevilFruitUiItem) -> Unit)?) {
        this.onFavoriteClickListener = onFavoriteClickListener
    }

    private val devilFruitList = mutableListOf<DevilFruitUiItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(devilFruitList: List<DevilFruitUiItem>) {
        with(this.devilFruitList) {
            clear()
            addAll(devilFruitList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevilFruitsViewHolder =
        DevilFruitsViewHolder(
            ItemDevilFruitsAdapterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = devilFruitList.size

    override fun onBindViewHolder(holder: DevilFruitsViewHolder, position: Int) {
        holder.bind(devilFruitList[position])
    }

    inner class DevilFruitsViewHolder(private val binding: ItemDevilFruitsAdapterBinding) :
        ViewHolder(binding.root) {
        fun bind(item: DevilFruitUiItem) = with(binding) {
            viewDevilFruitImage.loadOnBitmap(item.devilFruitPictureURL)
            viewInfoDevilFruitName.setInfo(item.devilFruitName)
            viewInfoDevilFruitType.setInfo(item.devilFruitType)
            ivDevilFruitFav.setImageResource(if (item.isFavorite) R.drawable.ic_remove_favorite else R.drawable.ic_add_favorite)
            ivDevilFruitFav.setOnClickListener {
                onFavoriteClickListener?.invoke(item)
                ivDevilFruitFav.setImageResource(if (item.isFavorite.not()) R.drawable.ic_remove_favorite else R.drawable.ic_add_favorite)
            }
        }
    }
}
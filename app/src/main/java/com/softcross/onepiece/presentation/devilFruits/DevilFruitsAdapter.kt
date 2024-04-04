package com.softcross.onepiece.presentation.devilFruits

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.softcross.onepiece.core.common.extension.loadOnBitmap
import com.softcross.onepiece.core.common.extension.loadOnBitmapWithResize
import com.softcross.onepiece.core.data.entity.DevilFruitEntity
import com.softcross.onepiece.databinding.ItemDevilFruitsAdapterBinding

class DevilFruitsAdapter : RecyclerView.Adapter<DevilFruitsAdapter.DevilFruitsViewHolder>() {

    private val devilFruitList = mutableListOf<DevilFruitEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(devilFruitList: List<DevilFruitEntity>) {
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

        fun bind(item: DevilFruitEntity) = with(binding) {
            viewDevilFruitImage.loadOnBitmap(item.devilFruitPictureURL)
            viewInfoDevilFruitName.setInfo(item.devilFruitName)
            viewInfoDevilFruitType.setInfo(item.devilFruitType)
        }

    }
}
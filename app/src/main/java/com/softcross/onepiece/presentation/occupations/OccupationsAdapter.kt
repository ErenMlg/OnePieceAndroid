package com.softcross.onepiece.presentation.occupations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softcross.onepiece.core.common.extension.loadOnBitmap
import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.databinding.ItemOccupationsAdapterBinding

class OccupationsAdapter : RecyclerView.Adapter<OccupationsAdapter.OccupationsViewHolder>() {

        private val occupationList = mutableListOf<Occupations>()

        @SuppressLint("NotifyDataSetChanged")
        fun updateItems(devilFruitList: List<Occupations>) {
            with(this.occupationList) {
                clear()
                addAll(devilFruitList)
            }
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OccupationsViewHolder =
            OccupationsViewHolder(
                ItemOccupationsAdapterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        override fun getItemCount(): Int = occupationList.size

        override fun onBindViewHolder(holder: OccupationsViewHolder, position: Int) {
            holder.bind(occupationList[position])
        }

        inner class OccupationsViewHolder(private val binding: ItemOccupationsAdapterBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(item: Occupations) = with(binding) {
                viewOccupationImage.loadOnBitmap(item.occupationPictureURL)
                viewInfoOccupationName.setInfo(item.occupationName)
                viewInfoOccupationDesc.setInfo(item.occupationDesc)
            }

        }
    }
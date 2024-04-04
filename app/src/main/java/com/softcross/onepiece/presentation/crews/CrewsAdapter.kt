package com.softcross.onepiece.presentation.crews

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.softcross.onepiece.core.common.extension.loadBitmapWithResize
import com.softcross.onepiece.core.data.entity.CrewEntity
import com.softcross.onepiece.databinding.ItemCrewAdapterBinding

class CrewsAdapter : RecyclerView.Adapter<CrewsAdapter.CrewsViewHolder>() {

    private var itemClickListener: ((Int) -> Unit)? = null

    private val crewList = mutableListOf<CrewUiItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(crewList: List<CrewUiItem>) {
        with(this.crewList) {
            clear()
            addAll(crewList)
        }
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: (Int) -> Unit) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrewsViewHolder =
        CrewsViewHolder(
            ItemCrewAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    fun getItemAtPosition(position: Int) = crewList[position]

    override fun getItemCount(): Int = crewList.size

    override fun onBindViewHolder(holder: CrewsViewHolder, position: Int) {
        holder.bind(crewList[position])
    }

    inner class CrewsViewHolder(private val binding: ItemCrewAdapterBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(item: CrewUiItem) = with(binding) {
            ivCrew.loadBitmapWithResize(item.crewImage, 500, 400)
            txtCrewName.text = item.crewName
        }
    }
}


package com.softcross.onepiece.presentation.locations.subLocations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.softcross.onepiece.core.common.extension.loadOnBitmap
import com.softcross.onepiece.core.data.entity.SubLocationEntity
import com.softcross.onepiece.databinding.ItemSubLocationsAdapterBinding

class SubLocationAdapter : RecyclerView.Adapter<SubLocationAdapter.SubLocationViewHolder>() {

    private val subLocationList = mutableListOf<SubLocationEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(subLocationList: List<SubLocationEntity>) {
        with(this.subLocationList) {
            clear()
            addAll(subLocationList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubLocationViewHolder =
        SubLocationViewHolder(
            ItemSubLocationsAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = subLocationList.size
    override fun onBindViewHolder(holder: SubLocationViewHolder, position: Int){
        holder.bind(subLocationList[position])
    }


    inner class SubLocationViewHolder(private val binding: ItemSubLocationsAdapterBinding) :
        ViewHolder(binding.root) {

        fun bind(item: SubLocationEntity) = with(binding) {
            viewInfoSubLocationFirstApperance.setInfo(item.firstAppearance)
            viewSubLocationImage.loadOnBitmap(item.subLocationPictureURL)
            txtSubLocationName.text = item.subLocationName
        }
    }

}
package com.softcross.onepiece.presentation.locations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softcross.onepiece.core.common.extension.loadBitmapWithResize
import com.softcross.onepiece.core.data.entity.LocationEntity
import com.softcross.onepiece.databinding.ItemLocationsAdapterBinding

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.LocationsViewHolder>() {

    private var itemClickListener: ((Int) -> Unit)? = null

    private val locationsList = mutableListOf<LocationEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(locationsList: List<LocationEntity>) {
        with(this.locationsList) {
            clear()
            addAll(locationsList)
        }
        notifyDataSetChanged()
    }

    fun setItemClickListener(itemClickListener: (Int) -> Unit) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder =
        LocationsViewHolder(
            ItemLocationsAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    fun getItemAtPosition(position: Int) = locationsList[position]

    override fun getItemCount(): Int = locationsList.size

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(locationsList[position])
    }

    inner class LocationsViewHolder(private val binding: ItemLocationsAdapterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                itemClickListener?.invoke(adapterPosition)
            }
        }

        fun bind(item: LocationEntity) = with(binding) {
            ivLocations.loadBitmapWithResize(item.locationPictureURL, 500, 400)
            txtLocationsName.text = item.locationName
        }
    }
}
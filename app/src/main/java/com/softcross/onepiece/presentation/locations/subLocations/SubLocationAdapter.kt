package com.softcross.onepiece.presentation.locations.subLocations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.extension.loadOnBitmap
import com.softcross.onepiece.core.data.modal.SubLocation
import com.softcross.onepiece.databinding.ItemSubLocationsAdapterBinding
import com.softcross.onepiece.presentation.occupations.OccupationUiItem

class SubLocationAdapter : RecyclerView.Adapter<SubLocationAdapter.SubLocationViewHolder>() {

    private var onFavoriteClickListener: ((subLocation: SubLocationUiItem) -> Unit)? = null

    fun setOnFavoriteClickListener(onFavoriteClickListener: ((subLocation: SubLocationUiItem) -> Unit)?) {
        this.onFavoriteClickListener = onFavoriteClickListener
    }

    private val subLocationList = mutableListOf<SubLocationUiItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(subLocationList: List<SubLocationUiItem>) {
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

        fun bind(item: SubLocationUiItem) = with(binding) {
            viewInfoSubLocationFirstApperance.setInfo(item.firstAppearance)
            viewSubLocationImage.loadOnBitmap(item.subLocationPictureURL)
            txtSubLocationName.text = item.subLocationName
            ivSubLocationFav.setImageResource(if (item.isFavorite) R.drawable.ic_remove_favorite else R.drawable.ic_add_favorite)
            ivSubLocationFav.setOnClickListener {
                onFavoriteClickListener?.invoke(item)
                ivSubLocationFav.setImageResource(if (item.isFavorite.not()) R.drawable.ic_remove_favorite else R.drawable.ic_add_favorite)
            }
        }
    }

}
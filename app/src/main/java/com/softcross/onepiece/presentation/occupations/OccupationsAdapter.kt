package com.softcross.onepiece.presentation.occupations

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softcross.onepiece.R
import com.softcross.onepiece.core.common.extension.loadOnBitmap
import com.softcross.onepiece.core.data.modal.Occupations
import com.softcross.onepiece.databinding.ItemOccupationsAdapterBinding
import com.softcross.onepiece.presentation.devilFruits.DevilFruitUiItem

class OccupationsAdapter : RecyclerView.Adapter<OccupationsAdapter.OccupationsViewHolder>() {

    private var onFavoriteClickListener: ((occupation: OccupationUiItem) -> Unit)? = null

    fun setOnFavoriteClickListener(onFavoriteClickListener: ((occupation: OccupationUiItem) -> Unit)?) {
        this.onFavoriteClickListener = onFavoriteClickListener
    }

    private val occupationList = mutableListOf<OccupationUiItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(occupationList: List<OccupationUiItem>) {
        with(this.occupationList) {
            clear()
            addAll(occupationList)
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

        fun bind(item: OccupationUiItem) = with(binding) {
            viewOccupationImage.loadOnBitmap(item.occupationPictureURL)
            viewInfoOccupationName.setInfo(item.occupationName)
            viewInfoOccupationDesc.setInfo(item.occupationDesc)
            ivOccupationFav.setImageResource(if (item.isFavorite) R.drawable.ic_remove_favorite else R.drawable.ic_add_favorite)
            ivOccupationFav.setOnClickListener {
                onFavoriteClickListener?.invoke(item)
                ivOccupationFav.setImageResource(if (item.isFavorite.not()) R.drawable.ic_remove_favorite else R.drawable.ic_add_favorite)
            }
        }

    }
}
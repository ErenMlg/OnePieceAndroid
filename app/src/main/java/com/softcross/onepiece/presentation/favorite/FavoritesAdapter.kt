package com.softcross.onepiece.presentation.favorite

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.softcross.onepiece.core.common.extension.loadBitmapWithResize
import com.softcross.onepiece.databinding.ItemFavoritesAdapterBinding
import com.softcross.onepiece.presentation.devilFruits.DevilFruitUiItem

class FavoritesAdapter : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    private var onDeleteFavoriteClickListener: ((favorites: FavoritesUiItem) -> Unit)? = null

    fun setOnDeleteFavoriteClickListener(onDeleteFavoriteClickListener: ((favorites: FavoritesUiItem) -> Unit)?) {
        this.onDeleteFavoriteClickListener = onDeleteFavoriteClickListener
    }

    private val favoritesList = mutableListOf<FavoritesUiItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateFavoriteList(favoritesList: List<FavoritesUiItem>) {
        with(this.favoritesList) {
            clear()
            addAll(favoritesList)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder =
        FavoritesViewHolder(
            ItemFavoritesAdapterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun getItemCount(): Int = favoritesList.size

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(favoritesList[position])
    }

    inner class FavoritesViewHolder(private val binding: ItemFavoritesAdapterBinding) :
        ViewHolder(binding.root) {
        fun bind(item: FavoritesUiItem) = with(binding) {
            ivFavorite.loadBitmapWithResize(item.imageURL, 600, 500)
            txtFavoriteName.text = item.name
            ivFavoriteTrash.setOnClickListener {
                onDeleteFavoriteClickListener?.invoke(item)
            }
        }
    }
}
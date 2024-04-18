package com.softcross.onepiece.presentation.characters.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.softcross.onepiece.databinding.ItemVideoAdapterBinding
import com.squareup.picasso.Picasso

class VideoViewHolder(
    private val binding: ItemVideoAdapterBinding,
    private val onVideoItemClickListener: (() -> Unit)?
) : ViewHolder(binding.root) {

    init {
        binding.viewPlay.setOnClickListener {
            onVideoItemClickListener?.invoke()
        }
    }

    fun bind() {
        Picasso.get()
            .load("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/25999bd5-73d5-46c9-9d9f-a3164450af95/d2504iz-49c0f350-42b9-40de-93a2-ee9b1b34b63a.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzI1OTk5YmQ1LTczZDUtNDZjOS05ZDlmLWEzMTY0NDUwYWY5NVwvZDI1MDRpei00OWMwZjM1MC00MmI5LTQwZGUtOTNhMi1lZTliMWIzNGI2M2EucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.CjOIkOPacKD5LpxusSb2GRLu3HHGJrfCBXrlHozYbPo")
            .into(binding.ivVideoItem)
    }

}
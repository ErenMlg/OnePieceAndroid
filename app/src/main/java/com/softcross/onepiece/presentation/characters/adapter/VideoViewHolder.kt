package com.softcross.onepiece.presentation.characters.adapter

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.softcross.onepiece.databinding.AdapterVideoItemBinding
import com.softcross.onepiece.presentation.characters.OnePieceItem
import com.squareup.picasso.Picasso

class VideoViewHolder(private val binding: AdapterVideoItemBinding) : ViewHolder(binding.root) {

    fun bind() {

        Picasso.get()
            .load("https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/ccf456e3-399d-4e68-9a00-888b379680ce/d4vego7-85c29152-36d9-43ed-af92-d9e50f5b5f4d.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcL2NjZjQ1NmUzLTM5OWQtNGU2OC05YTAwLTg4OGIzNzk2ODBjZVwvZDR2ZWdvNy04NWMyOTE1Mi0zNmQ5LTQzZWQtYWY5Mi1kOWU1MGY1YjVmNGQucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.WJ8IZB5KqhAZwp1_Y6CFU8PmhfZgn0uBhHmmNxK8-cA")
            .into(binding.ivVideoItem)

    }

}
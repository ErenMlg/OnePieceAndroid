package com.softcross.onepiece.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.softcross.onepiece.databinding.LayoutToolbarBinding

class ToolbarView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0
) :
    FrameLayout(context, attributeSet, defStyleAttr) {

    private val binding =
        LayoutToolbarBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
    }

    fun backClickListener(onItemClick: () -> Unit) {
        binding.imageView.setOnClickListener {
            onItemClick.invoke()
        }
    }

    fun setTitle(title: String) {
        binding.textView.text = title
    }

}
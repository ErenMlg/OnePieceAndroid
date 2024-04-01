package com.softcross.onepiece.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import com.softcross.onepiece.R
import com.softcross.onepiece.databinding.LayoutCharacterDetailInfoBinding

class DetailLayoutView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private val binding =
        LayoutCharacterDetailInfoBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        context.withStyledAttributes(attributeSet, R.styleable.CharacterInfoLayout) {
            val title = getString(R.styleable.CharacterInfoLayout_info_title)
            val image = getDrawable(R.styleable.CharacterInfoLayout_image)
            with(binding) {
                txtInfoTitle.text = title
                iwInfo.setImageDrawable(image)
                txtInfoValue.text = "maiorum latine voluptatum sem nostra phasellus aliquet posse delenit offendit dicam decore prompta voluptatum porro scripserit evertitur mea tota constituam"
            }
        }
    }

    fun setInfo(value: String) {
        binding.txtInfoValue.text = value
    }
}
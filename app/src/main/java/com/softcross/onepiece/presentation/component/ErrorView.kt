package com.softcross.onepiece.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.app.NotificationCompat.MessagingStyle.Message
import androidx.core.content.withStyledAttributes
import com.softcross.onepiece.R
import com.softcross.onepiece.databinding.LayoutErrorBinding

class ErrorView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    private val binding = LayoutErrorBinding.inflate(LayoutInflater.from(context), this, false)

    init {
        addView(binding.root)
        context.withStyledAttributes(attributeSet, R.styleable.ErrorView) {
            binding.txtError.text = getString(R.styleable.ErrorView_error_message)
        }
        binding.btnQuit.setOnClickListener {
            System.exit(0)
        }
    }

    fun setErrorMessage(errorMessage: String?) {
        binding.txtError.text = errorMessage
    }

    fun setRetryOnClick(onClick: () -> Unit) {
        binding.btnRetry.setOnClickListener { onClick() }
    }

}
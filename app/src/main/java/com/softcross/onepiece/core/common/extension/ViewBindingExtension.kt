package com.softcross.onepiece.core.common.extension

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> ViewGroup.inflateAdapterItem(
    crossinline inflater: (LayoutInflater, ViewGroup, Boolean) -> T,
    attachToParent: Boolean = false
): T {
    return inflater.invoke(LayoutInflater.from(context), this, attachToParent)
}
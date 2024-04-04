package com.softcross.onepiece.core.common.extension

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.core.graphics.drawable.toBitmapOrNull
import com.softcross.onepiece.presentation.characters.WantedBannerView
import com.softcross.onepiece.presentation.components.DetailBoxView
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import java.lang.Exception

fun DetailBoxView.loadOnBitmap(url: String) {
    Picasso.get().load(url).into(object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            setBitmap(bitmap)
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            setBitmap(errorDrawable?.toBitmapOrNull())
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            setBitmap(placeHolderDrawable?.toBitmapOrNull())
        }
    })
}

fun DetailBoxView.loadOnBitmapWithResize(url: String, height: Int, width: Int) {
    Picasso.get().load(url).resize(width, height).into(object : Target {
        override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
            setBitmap(bitmap)
        }

        override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            setBitmap(errorDrawable?.toBitmapOrNull())
        }

        override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            setBitmap(placeHolderDrawable?.toBitmapOrNull())
        }
    })
}

fun ImageView.loadBitmapWithResize(url: String, height: Int, width: Int) {
    Picasso.get().load(url).resize(width, height).into(this)
}

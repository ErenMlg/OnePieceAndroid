package com.softcross.onepiece.presentation.components

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.RadialGradient
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.softcross.onepiece.R
import kotlin.math.max
import kotlin.math.min

class DetailBoxView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr) {

    init {
        setWillNotDraw(false)
    }

    private var bitmap: Bitmap? = null

    private val bitmapMatrix = Matrix()

    private val viewRectF = RectF()

    private val bitmapRectF = RectF()

    private val framePath = Path()

    private val gradientPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var gradient: RadialGradient? = null

    private val imagePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        colorFilter = PorterDuffColorFilter(ContextCompat.getColor(context, R.color.transparentBlack), PorterDuff.Mode.DARKEN)
    }

    private val clipSpace = 16.toDp

    private val frameStrokeWidth = 8.toDp

    private val framePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.primaryColor)
        style = Paint.Style.STROKE
        strokeWidth = frameStrokeWidth.toFloat()
    }

    fun setBitmap(bitmap: Bitmap?) {
        this.bitmap = bitmap
        initImageMatrix()
        initFramePath()
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewRectF.set(0f, 0f, w.toFloat(), h.toFloat())
        gradient = RadialGradient(
            viewRectF.width() / 2,
            viewRectF.height() / 2,
            max(viewRectF.width(), viewRectF.height()),
            ContextCompat.getColor(context, R.color.subColor),
            ContextCompat.getColor(context, R.color.secondaryColor),
            Shader.TileMode.CLAMP
        )
        gradientPaint.shader = gradient
        initImageMatrix()
        initFramePath()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(framePath)
        canvas.drawPaint(gradientPaint)
        bitmap?.let { canvas.drawBitmap(it, bitmapMatrix, imagePaint) }
        canvas.drawPath(framePath, framePaint)
    }

    private fun initImageMatrix() {
        bitmap?.let {
            bitmapRectF.set(0f, 0f, it.width.toFloat(), it.height.toFloat())

            val widthScale = viewRectF.width() / bitmapRectF.width()
            val heightScale = viewRectF.height() / bitmapRectF.height()

            val scaleFactor = max(widthScale, heightScale)

            val bitmapTranslateX = (viewRectF.width() - scaleFactor * bitmapRectF.width()) / 2f
            val bitmapTranslateY = (viewRectF.height() - scaleFactor * bitmapRectF.height()) * 30/100

            bitmapMatrix.setScale(scaleFactor, scaleFactor)
            bitmapMatrix.postTranslate(bitmapTranslateX, bitmapTranslateY)
            invalidate()
        }
    }

    private fun initFramePath() {
        framePath.reset()

        framePath.moveTo(viewRectF.left, viewRectF.top + clipSpace)

        framePath.lineTo(viewRectF.left, viewRectF.bottom - clipSpace)

        framePath.lineTo(viewRectF.left + clipSpace, viewRectF.bottom)

        framePath.lineTo(viewRectF.right - clipSpace, viewRectF.bottom)

        framePath.lineTo(viewRectF.right, viewRectF.bottom - clipSpace)

        framePath.lineTo(viewRectF.right, viewRectF.top + clipSpace)

        framePath.lineTo(viewRectF.right - clipSpace, viewRectF.top)

        framePath.lineTo(viewRectF.left + clipSpace, viewRectF.top)

        framePath.lineTo(viewRectF.left, viewRectF.top + clipSpace)

        invalidate()
    }
}

val Int.toDp: Int get() = (this * Resources.getSystem().displayMetrics.density).toInt()
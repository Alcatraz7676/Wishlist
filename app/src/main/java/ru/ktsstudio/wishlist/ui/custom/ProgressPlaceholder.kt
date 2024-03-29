package ru.ktsstudio.wishlist.ui.custom

import android.content.Context
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import ru.ktsstudio.wishlist.R

class ProgressPlaceholder(context: Context) : CircularProgressDrawable(context) {

    init {
        ContextCompat.getColor(context, R.color.colorAccent).let(::init)
        start()
    }

    private fun init(@ColorInt color: Int) {
        setStyle(LARGE)
        setColorSchemeColors(color)
        strokeWidth = STROKE_WIDTH
        centerRadius = CENTER_RADIUS
    }


    companion object {
        private const val STROKE_WIDTH = 15F
        private const val CENTER_RADIUS = 90F
    }
}

package dev.olaore.animationscustomviews.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

class AttemptedIndicatorView @JvmOverloads
constructor(
        private val ctx: Context,
        private val attributeSet: AttributeSet? = null,
        private val defStyleAttr: Int = 0,
        private val defStyleRes: Int = 0
)
    : View(ctx, attributeSet, defStyleAttr, defStyleRes) {

    private var colorCount = 1
    private var colors = listOf(Color.BLACK, Color.CYAN, Color.BLUE, Color.GRAY, Color.GREEN)

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (i in 0 until colorCount) {
            drawSingleCardColor(canvas, i)
        }

    }

    fun provideColorCount(number: Int) {
        this.colorCount = number
        invalidate()
    }

    private fun drawSingleCardColor(canvas: Canvas?, cardIndex: Int) {

        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = colors[cardIndex]
        }

        canvas?.drawArc(
                0f, 0f, 200f, 200f, getAngleForIndex(cardIndex).toFloat(), (360 / colorCount).toFloat(), true, paint
        )

    }

    private fun getAngleForIndex(cardIndex: Int): Int {
        if (cardIndex == 0) {
            return -90
        }

        val sweepAngle = 360 / colorCount
        var finalAngle = 0

        finalAngle = (cardIndex * sweepAngle) - 90
        return finalAngle

    }

}

package dev.olaore.animationscustomviews.views

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import dev.olaore.animationscustomviews.R
import dev.olaore.animationscustomviews.models.Arc
import dev.olaore.animationscustomviews.models.Colors

class IndicatorView @JvmOverloads
    constructor(
            private val ctx: Context,
            private val attributeSet: AttributeSet? = null,
            private val defStyleAttr: Int = 0,
            private val defStyleRes: Int = 0
    )
    : View( ctx, attributeSet, defStyleAttr, defStyleRes ) {

    private val START_ANGLE = 225f
    private val rect = RectF(0f, 0f, 0f, 0f)

    private var arcs = emptyList<Arc>()
    private var colorMap = mapOf(
            Colors.COLOR_1 to ContextCompat.getColor(ctx, R.color.color_1),
            Colors.COLOR_2 to ContextCompat.getColor(ctx, R.color.color_2),
            Colors.COLOR_3 to ContextCompat.getColor(ctx, R.color.color_3),
            Colors.COLOR_4 to ContextCompat.getColor(ctx, R.color.color_4),
            Colors.COLOR_5 to ContextCompat.getColor(ctx, R.color.color_5)
    )
    private var defaultColor = ContextCompat.getColor(ctx, android.R.color.black)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.style = Paint.Style.FILL
        this.color = ContextCompat.getColor(ctx, R.color.color_1)
    }

    private var colors: List<Colors> = emptyList()
        set(value) {
            if (field != value || arcs.isEmpty()) {
                field = value
                computeArcs()
            }
        }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect.set(0f, 0f, w.toFloat(), h.toFloat())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        arcs.forEach { arc ->
            paint.color = arc.color
            canvas?.drawArc(
                    rect,
                    START_ANGLE + arc.start,
                    arc.sweep,
                    true,
                    paint
            )
        }
    }

    private fun computeArcs() {
        arcs = if (colors.isEmpty()) {
            listOf(Arc(0f, 360f, defaultColor))
        } else {
            val sweepAngle: Float = 360f / colors.size
            colors.mapIndexed { index, color ->
                val startAngle: Float = index * sweepAngle
                Arc(startAngle, sweepAngle, colorMap.getValue(color))
            }
        }
    }

}

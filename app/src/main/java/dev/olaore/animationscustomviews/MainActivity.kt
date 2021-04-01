package dev.olaore.animationscustomviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import dev.olaore.animationscustomviews.models.Colors
import dev.olaore.animationscustomviews.views.IndicatorView

class MainActivity : AppCompatActivity() {

    private lateinit var indicatorView: IndicatorView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indicatorView = findViewById(R.id.indicator_view)
        indicatorView.colors = listOf(Colors.COLOR_1, Colors.COLOR_3, Colors.COLOR_5)

    }
}
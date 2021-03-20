package dev.olaore.animationscustomviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import dev.olaore.animationscustomviews.views.IndicatorView

class MainActivity : AppCompatActivity() {

    private lateinit var indicatorView: IndicatorView
    private lateinit var colorCountEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        colorCountEditText = findViewById(R.id.color_count)
        indicatorView = findViewById(R.id.indicator_view)

        colorCountEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                try {
                    val number = p0.toString().toInt()
                    if (number > 5 || number < 0) {
                        Toast.makeText(applicationContext, "Number has to be more between 1 and 5", Toast.LENGTH_LONG).show()
                    } else {
                        indicatorView.provideColorCount(number)
                    }
                } catch(ex: Exception) {
                    Log.d("MainActivity", "Something occurred!")
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }
}
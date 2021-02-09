package es.uji.al315542.TemperatureConverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var celsiusEditText: EditText
    lateinit var fahrenheitEditText: EditText
    var changing = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        celsiusEditText = findViewById(R.id.celsiusEditText)
        celsiusEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (!changing){
                    onCelsiusChanged(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })


        fahrenheitEditText = findViewById(R.id.fahrenheitEditText)
        fahrenheitEditText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (!changing){
                    onFahrenheitChanged(s.toString())
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    private fun changeText(editText: EditText, str: String){
        changing = true
        editText.setText(str)
        changing = false
    }

    private fun onCelsiusChanged(str: String) {
        val celsius = str.toDoubleOrNull()
        if (celsius == null){
            changeText(fahrenheitEditText, "")
            return
        }
        val fahrenheit = 32 + celsius / 100 * 180
        changeText(fahrenheitEditText, fahrenheit.toString())
    }

    private fun onFahrenheitChanged(str: String) {
        val fahrenheit = str.toDoubleOrNull()
        if (fahrenheit == null){
            changeText(celsiusEditText, "")
            return
        }
        val celsius = fahrenheit - 32 * 100 / 180
        changeText(celsiusEditText, celsius.toString())
    }
}
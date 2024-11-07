package com.example.lab50

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.TextView
import android.widget.Toast
import android.view.View
import android.content.Intent

class MainActivity : AppCompatActivity() {

    //private lateinit var pages: EditText

    //private lateinit var a4: RadioButton
    //private lateinit var a3: RadioButton
    //private lateinit var a1: RadioButton

    //private lateinit var price4: TextView
    //private lateinit var price3: TextView
    //private lateinit var price1: TextView

    //private lateinit var summa: TextView

    //private lateinit var result: Button

    private lateinit var editTextPages: EditText
    private lateinit var radioGroupFormat: RadioGroup
    private lateinit var buttonCalculate: Button
    private lateinit var textViewResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        editTextPages = findViewById(R.id.editTextNumber)
       // radioGroupFormat = findViewById(R.id.radioGroupFormat)
        radioGroupFormat = findViewById(R.id.radioGroupFormat)
        buttonCalculate = findViewById(R.id.button2)
        textViewResult = findViewById(R.id.textView9)

        //pages = findViewById(R.id.editTextNumber)

       // a4 = findViewById(R.id.radioButton)
       // a3 = findViewById(R.id.radioButton2)
       // a1 = findViewById(R.id.radioButton3)

       // price4 = findViewById(R.id.textView5)
       // price3 = findViewById(R.id.textView6)
       // price1 = findViewById(R.id.textView7)

       // summa = findViewById(R.id.textView9)

       // result = findViewById(R.id.button2)
        buttonCalculate.setOnClickListener { solve() }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun solve(){
        // Получаем количество страниц
        val pagesText = editTextPages.text.toString()
        if (pagesText.isEmpty()) {
            Toast.makeText(this, "Введите количество страниц", Toast.LENGTH_SHORT).show()
            return
        }

        val pages = pagesText.toInt()

        // Определяем цену за страницу в зависимости от выбранного формата
        val pricePerPage = when (radioGroupFormat.checkedRadioButtonId) {
            R.id.radioButton -> 10   // A4 - 10 руб./стр.
            R.id.radioButton2 -> 30  // A3 - 30 руб./стр.
            R.id.radioButton3 -> 100 // A1 - 100 руб./стр.
            else -> {
                Toast.makeText(this, "Выберите формат листа", Toast.LENGTH_SHORT).show()
                return
            }
        }

        // Вычисляем общую стоимость
        val totalCost = pages * pricePerPage

        val intent = Intent(this, result::class.java).apply{
            putExtra("RESULT_COST", totalCost)
        }
        startActivity (intent)
        // Устанавливаем результат в TextView
        //textViewResult.text = "Сумма заказа = $totalCost руб."

        //textViewResult.visibility = View.VISIBLE // Делаем TextView видимым
    }
}